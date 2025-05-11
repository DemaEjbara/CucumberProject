package DefinitionSteps.Steps;
import DefinitionSteps.Steps.Hooks.DriverInitialization;
import Page.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class AddToCartSteps {
    WebDriverWait wait;
    HomePage pomHomePage;
    public AddToCartSteps() {
        pomHomePage = new HomePage(DriverInitialization.driver);
        wait=new WebDriverWait(DriverInitialization.driver, Duration.ofSeconds(30));
    }
    @When("Press add to cart for {string}")
    public void pressAddToCartFor(String itemName) {
        boolean itemFound = false;
        /*
         * Retrieves all "ADD TO CART" buttons from the home page.
         * Iterates through the list of items and checks each item's name
         * to determine whether it matches the user's desired item to add to the cart.
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
    /**
     * Verifies that the cart number is updated to the expected value.
     * Waits until the cart number element reflects the specified value. If the update does not
     * occur within the wait timeout, or if an unexpected error occurs, the test fails.
     *
     * @param expectedCartNumber the expected number of items in the cart as a string
     */
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
    /**
     * Simulates the user action of clicking the "Remove" button for the Sauce Labs Fleece Jacket.
     * After clicking, it asserts that the "Remove" button is displayed, confirming the state change
     * from "Add to Cart" to "Remove".
     */
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
}
