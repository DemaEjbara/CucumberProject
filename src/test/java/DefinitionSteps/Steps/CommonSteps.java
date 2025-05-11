package DefinitionSteps.Steps;
import DefinitionSteps.Steps.Hooks.DriverInitialization;
import HelpersUtils.locatorUtil;
import Page.CheckOutYourInformation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class CommonSteps {
    WebDriverWait wait;
     CheckOutYourInformation pomCheckout;
     public CommonSteps(){
         wait=new WebDriverWait(DriverInitialization.driver,Duration.ofSeconds(10));
         pomCheckout=new CheckOutYourInformation(DriverInitialization.driver);
     }
    /**
     * Sends the specified text input into a field identified by its key.
     * Locates the input field using a key from the locator utility, waits for it to be visible,
     * clears any existing text, and sends the provided input
     *
     * @param text     the text to input
     * @param fieldKey the key used to locate the input field
     */
    @Then("Insert {string} into {string}")
    public void sendKeys(String text, String fieldKey) {
        By locator = locatorUtil.getLocater(fieldKey);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(text);
    }
    /**
     * Verifies that the browser has navigated to the expected URL.
     * Waits for the current URL to match the expected URL. If the expected URL does not load
     * within the wait period, the test fails and logs the actual URL.
     *
     * @param expectedUrl the URL the browser is expected to be redirected to
     */
    @Then("Redirection to {string}")
    public void redirectionTo(String expectedUrl) {
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (TimeoutException e) {
            String actualUrl = DriverInitialization.driver.getCurrentUrl();
            Assert.fail("Timeout waiting for URL to be '" + expectedUrl + "'. Actual URL: " + actualUrl);
        }
        String actualUrl = DriverInitialization.driver.getCurrentUrl();
        Assert.assertEquals("The Page URL is not displayed correctly", expectedUrl, actualUrl);
    }
    /**
     * Verifies that the error button is displayed on the checkout page.
     * Waits until the error button becomes visible and asserts its display status.
     */
    @Then("Error button should  be displayed")
    public void error_button_should_be_displayed() {
        WebElement errorButton = pomCheckout.errorButtonIsDisplayed();
        wait.until(ExpectedConditions.visibilityOf(errorButton));
        Assert.assertTrue("The error button should be displayed", errorButton.isDisplayed());
    }
    /**
     * Clicks on a button identified by the given key.
     * Waits for the button to be clickable before clicking it.
     *
     * @param key the key used to locate the button
     * @throws InterruptedException if the thread sleep is interrupted
     */
    @When("Press on {string} button")
    public void pressOnButton(String key) throws InterruptedException {
        By locator = locatorUtil.getLocater(key);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(locator));
        button.click();
        Thread.sleep(2000);}
    /**
     * Verifies that the page contains a title matching the expected text
     * Locates the title element using a key, retrieves the actual and expected title texts,
     * and asserts their equality.
     *
     * @param titleKey the key used to locate and retrieve the title text
     */
    @And("this page should contains title as {string}")
    public void theThisPageShouldContainsTitleAs(String titleKey) {
        By locator = locatorUtil.GetLocater(titleKey);
        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        String expectedTitle = locatorUtil.getText(titleKey);
        Assert.assertEquals("Expected title text does not match.", expectedTitle, actualTitle);
    }
}
