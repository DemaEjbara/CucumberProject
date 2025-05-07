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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import utils.locatorUtil;
public class StepDefinition {
    static WebDriverWait  wait;
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
        wait=new WebDriverWait(driver, Duration.ofSeconds(30));

        return driver;
    }
    @Before
    public void setupScenario() {
        driver = setupWebDriver();
        open_the_swag_labs_website();
    }
    @After
    public void teardownScenario() {
        if (driver != null) {
            driver.quit();
        }
    }
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
    @Then("Successful Login")
    public void successful_login() {
        try{
        String expectUrl="https://www.saucedemo.com/v1/inventory.html";
        String currentUrl=pomHomePage.pageUrl();
        assertEquals( "home page url" ,expectUrl,currentUrl);
        }
    catch(Exception e){
        System.out.println("Unexpected Error :" +e.getMessage());}
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

        sortedFullyOfItems(sortType,productNames,productPrices);
    }
    public void sortedFullyOfItems (String sortType , List<String > productNames,List<Double> productPrices){
        switch (sortType)
        {
            case "A to Z":
                List<String> sortedNamesAsc = new ArrayList<>(productNames);
                Collections.sort(sortedNamesAsc);
                assertEquals("Products are not sorted A to Z", sortedNamesAsc, productNames);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedAscAlpha(sortedNamesAsc));

                break;
            case "Z to A":
                List<String> sortedNamesDesc = new ArrayList<>(productNames);
                sortedNamesDesc.sort(Collections.reverseOrder());
                assertEquals("Products are not sorted Z to A", sortedNamesDesc, productNames);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedDescAlpha(sortedNamesDesc));
                break;
            case "Low to High":
                List<Double> sortedPricesAsc = new ArrayList<>(productPrices);
                Collections.sort(sortedPricesAsc);
                assertEquals("Products are not sorted Low to High", sortedPricesAsc, productPrices);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedAsc(sortedPricesAsc));
                break;
            case "High to Low":
                List<Double> sortedPricesDesc = new ArrayList<>(productPrices);
                sortedPricesDesc.sort(Collections.reverseOrder());
                assertEquals("Products are not sorted High to Low", sortedPricesDesc, productPrices);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedDesc(sortedPricesDesc));
                break;
            default:
                Assert.fail("Unsupported sort type: " + sortType);
        }
    }
    @Given("The {string} item is available")
    public void theItemIsAvailable(String nameElement) {
        for(int i=0;i<pomHomePage.itemOfElements().size();i++){
        assertTrue("this element"+pomHomePage.itemOfElements().get(i).getText()+ "is not available ",pomHomePage.itemOfElements().get(i).isDisplayed());}
    }

    /**
     * Example of method documentation
     * @param itemName
     */
    @When("Press add to cart for {string}")
    public void pressAddToCartFor(String itemName) {
                boolean itemFound = false;
                /*
                Example of documentation for List
                 */
                List<WebElement> addButtons = pomHomePage.buttonElementList();
                for (int i = 0; i < pomHomePage.nameOfElements().size(); i++) {
                    WebElement currentElement = pomHomePage.nameOfElements().get(i);
                    wait.until(ExpectedConditions.visibilityOf(currentElement));
                    String currentItem=currentElement.getText();
                    if (currentItem.equalsIgnoreCase(itemName)) {
                        WebElement addButton=addButtons.get(i);
                        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click(); //Example of line documentation
                        itemFound = true;
                        break;
                    }
                }

                if (!itemFound) {
                    Assert.fail("Item '" + itemName + "' was not found on the page to add to the cart.");
                }
        }

    @Then("cart number changes to {string}")
    public void cart_number_changes_to(String expectedCartNumber) {
       try {
            boolean isCartUpdated = wait.until(ExpectedConditions.textToBePresentInElement(pomHomePage.cartnumber(), expectedCartNumber));
            if (!isCartUpdated) {
                Assert.fail("Cart number is not update to '" + expectedCartNumber + "' in expected time.");
            }
            String actualCartNumber = pomHomePage.cartnumber().getText();
            assertEquals("The cart item count is not updated", expectedCartNumber, actualCartNumber);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            Assert.fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

        @Then("Redirection to {string} and contains title {string}")
    public void redirectionTo(String expectedUrl ,String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        By locator = locatorUtil.getLocater(expectedTitle);
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (TimeoutException e) {
            String actualUrl = driver.getCurrentUrl();
            Assert.fail("Timeout waiting for URL to be '" + expectedUrl + "'. Actual URL: " + actualUrl);
        }
        boolean isTextPresent = wait.until(ExpectedConditions.textToBe(locator, expectedTitle));
        Assert.assertTrue("Expected title text was not found on the page.", isTextPresent);
        String actualUrl = driver.getCurrentUrl();
        assertEquals("The Page URL is not displayed correctly", expectedUrl, actualUrl);
    }
//    @Then("Insert first name {string} , Last name : {string} and postal code : {string}")
//    public void insert_first_name_last_name_and_postal_code(String name, String lastName, String postalCode) {
//        pomCheckout.firstnameField().sendKeys(name);
//        pomCheckout.lastnameField().sendKeys(lastName);
//        pomCheckout.postalCodeField().sendKeys(postalCode);
//    }

    @Then("Error button should  be displayed")
    public void error_button_should_be_displayed() {
        WebElement errorButton = pomCheckout.errorButtonIsDisplayed();
        wait.until(ExpectedConditions.visibilityOf(errorButton));
        Assert.assertTrue("The error button should be displayed", errorButton.isDisplayed());
    }
    @Then("Check price {double}")
    public void check_price(Double expectedPrice) {
        String totalPriceText = pomCheckOverview.totalPrice().getText();
        String priceWithoutPrefix = totalPriceText.replace("Total: ", "").trim();
        String priceWithoutDollar = priceWithoutPrefix.replace("$", "").trim();
        try {
            double actualPrice = Double.parseDouble(priceWithoutDollar);
            assertEquals("The actual price does not match the expected price!", expectedPrice, actualPrice, 0.001);
        } catch (NumberFormatException e) {
            Assert.fail("Failed to parse the actual price: " + totalPriceText + " - " + e.getMessage());
        }
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
    @When("Press on {string} button")
    public void pressOnButton(String key) {
        By locator = locatorUtil.getLocater(key);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
        button.click();

//        switch (nameButton){
//            case "login":
//                pomLoginPage.loginButton().click();
//                break;
//            case "cart icon" :
//                pomHomePage.cartIcon().click();
//                break;
//            case "sorting drop down":
//                wait.until(ExpectedConditions.visibilityOfAllElements(pomHomePage.itemOfElements()));
//                pomHomePage.sortingDropDown().click();
//                break;
//            case "check out" :
//                pomYourCart.checkoutButton().click();
//                break;
//            case "continue" :
//                pomCheckout.continueButton().click();
//                break;
//            case "finish" :
//                pomCheckOverview.finishButton().click();
//                break;
//        }
    }
//    @When("Press on {string} button")
//    public void pressONButton (String nameButton){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        Map<String, WebElement> buttons = Map.of(
//                "login", pomLoginPage.loginButton(),
//                "cart icon", pomHomePage.cartIcon(),
//                "check out", pomYourCart.checkoutButton(),
//                "continue", pomCheckout.continueButton(),
//                "finish", pomCheckOverview.finishButton()
//        );
//        if ("sorting drop down".equals(nameButton)) {
//            wait.until(ExpectedConditions.visibilityOfAllElements(pomHomePage.itemOfElements()));
//            pomHomePage.sortingDropDown().click();
//        } else if (buttons.containsKey(nameButton)) {
//            WebElement button = buttons.get(nameButton);
//            wait.until(ExpectedConditions.elementToBeClickable(button));
//            button.click();
//        }
//    }

    /**
     * THis Method....
     * @param text
     * @param fieldKey
     */
    @Then("Insert {string} into {string}")
public void sendKeys(String text, String fieldKey) {
    By locator = locatorUtil.getLocater(fieldKey);
    WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    field.clear();
    field.sendKeys(text);
   // WebElement f=wait.until(ExpectedConditions.textToBe(locator,expectedTitle));
}

    }






