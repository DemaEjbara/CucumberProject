package DefinitionSteps.Steps;
import DefinitionSteps.Steps.Hooks.DriverInitialization;
import Page.HomePage;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertTrue;
public class HomePageSteps {
    HomePage pomHomePage;
    public HomePageSteps() {
        pomHomePage = new HomePage(DriverInitialization.driver);
    }
    /**
     * Verifies that all item elements on the home page are visible.
     * Iterates through the list of item elements retrieved from the page and asserts
     * that each one is displayed. Fails the test if any item is not visible.
     *
     * @param nameElement the name of the item (currently unused in the method)
     */
    @Given("The {string} item is available")
    public void theItemIsAvailable(String nameElement) {
        for(int i=0;i<pomHomePage.itemOfElements().size();i++){
            assertTrue("this element"+pomHomePage.itemOfElements().get(i).getText()+ "is not available ",pomHomePage.itemOfElements().get(i).isDisplayed());}
    }
}
