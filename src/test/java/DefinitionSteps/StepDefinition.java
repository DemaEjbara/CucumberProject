package DefinitionSteps;
import Page.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.*;
import static org.junit.Assert.assertTrue;
public class StepDefinition {
    public static WebDriver driver ;
    HomePage pomHomePage ;
    YourCart pomYourCart ;
    LoginPagePOM pomLoginPage ;
    CheckOutYourInformation pomCheckout;
    CheckOutOverview pomCheckOverview ;
    CheckOutComplete pomComplete ;
    public static WebDriver setupWebDriver() {
        File driverFile = new File("src/test/drivers/chromedriver");
        if (!driverFile.exists()) {
            throw new RuntimeException("ChromeDriver not found at " + driverFile.getAbsolutePath());
        }
        System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments(
                "--disable-blink-features=AutomationControlled",
                "--disable-features=PasswordCheck, PasswordLeakDetection, SafetyTipUI",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--no-default-browser-check"
        );
        System.out.println("Launching Chrome with options: " + options);
        driver = new ChromeDriver(options);
        return driver;
    }
    @Before
    public void setupScenario() {
        driver = setupWebDriver();
    }
    @After
    public void teardownScenario() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Given("Open the Swag Labs Website")
    public void open_the_swag_labs_website() {
        pomHomePage = new HomePage(driver);
        pomYourCart = new YourCart(driver);
        pomLoginPage = new LoginPagePOM(driver);
        pomCheckout = new CheckOutYourInformation(driver);
        pomCheckOverview = new CheckOutOverview(driver);
        pomComplete = new CheckOutComplete(driver);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/index.html");
    }
    @When("Enter  {string} in the username field on Login Screen")
    public void enter_in_the_username_field_on_login_screen(String username) {
        pomLoginPage.userName().sendKeys(username);
    }
    @And("Enter  {string} in the password field on Login Screen")
    public void enter_in_the_password_field_on_login_screen(String password) {
        pomLoginPage.passWord().sendKeys(password);
    }
    @And("Press the login button")
    public void press_the_login_button() {
        pomLoginPage.loginButton().click();
    }
    @Then("Successful Login")
    public void successful_login() {
        String expectUrl="https://www.saucedemo.com/v1/inventory.html";
        String currentUrl=pomHomePage.pageUrl();
        Assert.assertEquals( "home page url" ,expectUrl,currentUrl);}
    @Given("Press the sorting drop down")
    public void press_the_sorting_drop_down() {
        pomHomePage.sortingDropDown().click();
    }
    @When("User choose name {string}")
    public void user_choose_name(String type) {
        switch (type){
            case "A to Z" :
                pomHomePage.nameAtoZ().click();
                break;
            case "Z to A" :
                pomHomePage.nameZtoA().click();
                break;
            case "Low to High" :
                pomHomePage.nameLowToHigh().click();
                break;
            case "High to Low" :
                pomHomePage.nameHighToLow().click();
                break;
        }
    }
    @Given("Products are sorted from {string} successfully")
    public void products_are_sorted_from_successfully(String sortType) {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item")));
        List<WebElement> productNamesElements=driver.findElements((By.className("inventory_item_name")));
        List<String> productNames=new ArrayList<>();
        for(WebElement element : productNamesElements){
            productNames.add(element.getText());
        }
        List<WebElement> productPriceElements=driver.findElements(By.className("inventory_item_price"));
        List<Double> productPrices=new ArrayList<>();
        for(WebElement element : productPriceElements){
            String priceText=element.getText().replace("$","");
            String priceWithoutPrefix = priceText.replace("Total: ", "").trim();
            String priceWithoutDollar = priceWithoutPrefix.replace("$", "").trim();
            try{
                productPrices.add(Double.parseDouble(priceWithoutDollar));
            }
            catch (NumberFormatException e){
                Assert.fail("could not parse price "+ priceWithoutDollar);
            }
        }
        switch (sortType)
        {
            case "A to Z":
                List<String> sortedNamesAsc = new ArrayList<>(productNames);
                Collections.sort(sortedNamesAsc);
                Assert.assertEquals("Products are not sorted A to Z", sortedNamesAsc, productNames);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedAscAlpha(sortedNamesAsc));
                break;
            case "Z to A":
                List<String> sortedNamesDesc = new ArrayList<>(productNames);
                Collections.sort(sortedNamesDesc, Collections.reverseOrder());
                Assert.assertEquals("Products are not sorted Z to A", sortedNamesDesc, productNames);
                Assert.assertTrue("actual result are not sorted by Desc sorting",isSortedDescAlpha(sortedNamesDesc));
                break;
            case "Low to High":
                List<Double> sortedPricesAsc = new ArrayList<>(productPrices);
                Collections.sort(sortedPricesAsc);
                Assert.assertEquals("Products are not sorted Low to High", sortedPricesAsc, productPrices);
                Assert.assertTrue("actual result is not sorted from low to high",isSortedAsc(sortedPricesAsc));
                break;
            case "High to Low":
                List<Double> sortedPricesDesc = new ArrayList<>(productPrices);
                Collections.sort(sortedPricesDesc, Collections.reverseOrder());
                Assert.assertEquals("Products are not sorted High to Low", sortedPricesDesc, productPrices);
                Assert.assertTrue("actual result is not sorted from high to low",isSortedDesc(sortedPricesDesc));
                break;
            default:
                Assert.fail("Unsupported sort type: " + sortType);
        }
    }
    @Given("The sauce labs backPack item is available")
    public void the_sauce_labs_back_pack_item_is_available() {
        assertTrue("the sauce labs backPack is not available ",pomHomePage.sauceLabsBackPackAvailable().isDisplayed());
    }
    @When("Press add to cart for Sauce Labs Backpack")
    public void press_add_to_cart_for_sauce_labs_backpack() {
        try{
            pomHomePage.sauceLabsBackpackAdd().click();
        }
        catch (Exception e){
            assertTrue("there is no element sauce labs back pack " , false);
        }
    }
    @Then("cart number changes to {string}")
    public void cart_number_changes_to(String cartNumber) {
        try {
            String counterCart=pomHomePage.cartnumber().getText();
            Assert.assertEquals(counterCart,"1" , "the cart item count is not updated ");
        } catch (Exception e) {
            System.out.println("Unexpected error "+ e.getMessage());
            Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }
    @Given("Press on cart icon on the top right of the page")
    public void press_on_cart_icon_on_the_top_right_of_the_page() {
        pomHomePage.cartIcon().click();
    }
    @Then("Redirection to the cart page")
    public void redirection_to_the_cart_page() {
        String expectUrl ="https://www.saucedemo.com/v1/cart.html";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals("Cart Page Url ", expectUrl,actualUrl);
        String expectTitle="Your Cart";
        String actualPageTitle=pomYourCart.cartPageTitle().getText();
        Assert.assertEquals("cart page title", expectTitle , actualPageTitle);
    }
    @Then("Press check out button")
    public void press_check_out_button() {
        pomYourCart.checkoutButton().click();
    }
    @Then("Redirection to the Checkout : Your Information")
    public void redirection_to_the_checkout_your_information() {
        String expectUrl ="https://www.saucedemo.com/v1/checkout-step-one.html";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectUrl,actualUrl);
    }
    @Then("Insert first name {string} , Last name : {string} and postal code : {string}")
    public void insert_first_name_last_name_and_postal_code(String name, String lastName, String postalCode) {
        pomCheckout.firstnameField().sendKeys(name);
        pomCheckout.lastnameField().sendKeys(lastName);
        pomCheckout.postalCodeField().sendKeys(postalCode);
    }
    @Then("Click Continue")
    public void click_continue() {
        pomCheckout.continueButton().click();
    }
    @Then("Error button should  be displayed")
    public void error_button_should_be_displayed() {
        Assert.assertTrue("the error button should be displayed", pomCheckout.errorButtonIsDisplayed().isDisplayed());
    }
    @Then("Redirection to Checkout : Overview")
    public void redirection_to_checkout_overview() {
        String expectUrl ="https://www.saucedemo.com/v1/checkout-step-two.html";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectUrl,actualUrl);
    }
    @Then("Check price {double}")
    public void check_price(Double expectedPrice) {
        String totalPriceText = pomCheckOverview.totalPrice().getText();
        String priceWithoutPrefix = totalPriceText.replace("Total: ", "").trim();
        String priceWithoutDollar = priceWithoutPrefix.replace("$", "").trim();
        try {
            Double actualPrice = Double.parseDouble(priceWithoutDollar);
            Assert.assertEquals("The actual price does not match the expected price!", expectedPrice, actualPrice, 0.001);
        } catch (NumberFormatException e) {
            Assert.fail("Failed to parse the actual price: " + totalPriceText + " - " + e.getMessage());
        }
    }
    @And("Wait for {int}")
    public void wait_for(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("Press finish")
    public void press_finish() {
        pomCheckOverview.finish().click();
    }
    @Then("Check Checkout : Complete!")
    public void check_checkout_complete() {

        String expectUrl ="https://www.saucedemo.com/v1/checkout-complete.html";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectUrl,actualUrl);
    }
    public boolean isSortedAsc(List<Double>prices){
        for (int i=0 ; i<prices.size()-1;i++){
            if (prices.get(i) >prices.get(i+1))
                return false;
        }
        return true;
    }
    public boolean isSortedDesc(List<Double>prices){
        for(int i=0 ;i<prices.size()-1;i++){
            if(prices.get(i)<prices.get(i+1))
                return false;
        }
        return true;
    }
    public boolean isSortedAscAlpha(List<String>productNames){
        for(int i=0 ;i<productNames.size()-1;i++){
            if(productNames.get(i).compareTo(productNames.get(i+1))>0)
                return false;
        }
        return true;
    }
    public boolean isSortedDescAlpha(List<String>productNames){
        for(int i=0 ;i<productNames.size()-1;i++){
            if(productNames.get(i).compareTo(productNames.get(i+1))<0)
                return false;
        }
        return true;
    }
    @Given("I click on add to cart button for Sauce Labs Fleece Jacket")
    public void i_click_on_add_to_cart_button_for_sauce_labs_fleece_jacket() {
        pomHomePage.sauceLabsFleeceJacketAdd().click();
    }
    @When("Add to cart changed to remove")
    public void add_to_cart_changed_to_remove() {
        try{
            pomHomePage.removeSauceLabsFleeceJacket().click();
            Assert.assertTrue("remove button for sauce labs fleece jacket is not display", pomHomePage.removeSauceLabsFleeceJacket().isDisplayed());
        }
        catch(Exception e){
            System.out.println("UnExpected Error : remove  button of the sauce labs fleece jacket is not display ");
        }
    }
    @Then("cart number change to {string} item")
    public void cart_number_change_to_item(String count) {
        String counterCart=pomHomePage.cartnumber().getText();
        Assert.assertEquals("the cart item count is not updated", count, counterCart);
    }

    @Given("The sauce labs Bolt T-Shirt item is available")
    public void theSauceLabsBoltTShirtItemIsAvailable() {
        assertTrue("the sauce labs bolt t-shirt is not displayed",pomHomePage.sauceLabsBoltTShirt().isDisplayed());
    }

    @When("Press add to cart for Sauce Labs Bolt T-Shirt")
    public void pressAddToCartForSauceLabsBoltTShirt() {
        try{
            pomHomePage.sauceLabsBoltTShirt().click();
        }
        catch (Exception e){
            assertTrue("there is no element sauce labs bolt t-shirt " , false);
        }
    }
}
