package DefinitionSteps.Steps;
import DefinitionSteps.Steps.Hooks.DriverInitialization;
import Page.CheckOutOverview;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
public class OverviewSteps {
    CheckOutOverview pomCheckOverview;
    public OverviewSteps(){
        pomCheckOverview=new CheckOutOverview(DriverInitialization.driver);
    }
    /**
     * Verifies that the total price displayed on the checkout overview page matches the expected value.
     * <p>
     * This step extracts and parses the price string from the UI (removing any text and currency symbols),
     * converts it to a double, and asserts it equals the expected price with a small delta for floating-point precision.
     *
     * @param expectedPrice the expected total price to be displayed
     */
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
}
