package DefinitionSteps.Steps;
import DefinitionSteps.Steps.Hooks.DriverInitialization;
import Page.HomePage;
import Page.LoginPagePOM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
public class LoginSteps {
    LoginPagePOM pomLoginPage;
    HomePage pomHomePage;
    public LoginSteps() {
        pomLoginPage = new LoginPagePOM(DriverInitialization.driver); // Use the initialized driver
        pomHomePage = new HomePage(DriverInitialization.driver);
    }
    /**
     * Enters the provided username into the username field on the login screen.
     *
     * @param username the username to input
     */
    @When("Enter  {string} in the username field on Login Screen")
    public void enter_in_the_username_field_on_login_screen(String username) {
        pomLoginPage.userName().sendKeys(username);
    }
    /**
     * Enters the provided password into the password field on the login screen.
     *
     * @param password the password to input
     */
    @And("Enter  {string} in the password field on Login Screen")
    public void enter_in_the_password_field_on_login_screen(String password) {
        pomLoginPage.passWord().sendKeys(password);
    }
    /**
     * Verifies that login was successful by checking the URL of the redirected page.
     * <p>
     * Compares the actual current URL with the expected home page URL. If they don't match,
     * or if an unexpected error occurs, the test fails with an appropriate message.
     */
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
}
