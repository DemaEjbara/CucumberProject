package DefinitionSteps.Steps;
import DefinitionSteps.Steps.Hooks.DriverInitialization;
import HelpersFunctions.HelperSort;
import Page.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class SortItemsSteps {
    static WebDriverWait  wait;
    public static WebDriver driver ;
    HomePage pomHomePage ;
    HelperSort helper;
    public SortItemsSteps(){
        pomHomePage=new HomePage(DriverInitialization.driver);
        helper=new HelperSort();
    }
    /**
     * Simulates the user selecting a sort option from the product sorting dropdown.
     * Based on the input string, the appropriate sorting option (e.g., A to Z, Z to A, Low to High, High to Low)
     * is selected by triggering the corresponding UI action.
     *
     * @param type the sort option to select (e.g., "A to Z", "Z to A", "Low to High", "High to Low")
     */
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
    /**
     * Verifies that products are sorted correctly on the page based on the given sort type.
     * Waits until all product elements are visible, collects their names and prices,
     * parses the prices to numerical values, and then delegates the actual sorting verification
     * to a helper method.
     *
     * @param sortType the expected sort order (e.g., "A to Z", "Low to High")
     */
    @Given("Products are sorted from {string} successfully")
    public void products_are_sorted_from_successfully(String sortType) {
        WebDriverWait wait=new WebDriverWait(DriverInitialization.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item")));
        List<WebElement> productNamesElements=DriverInitialization.driver.findElements((By.className("inventory_item_name")));
        List<String> productNames=new ArrayList<>();
        for(WebElement element : productNamesElements){
            productNames.add(element.getText());
        }
        List<WebElement> productPriceElements=DriverInitialization.driver.findElements(By.className("inventory_item_price"));
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
        helper.sortedFullyOfItems(sortType,productNames,productPrices);
    }
}
