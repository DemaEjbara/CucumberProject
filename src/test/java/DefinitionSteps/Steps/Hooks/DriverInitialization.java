package DefinitionSteps.Steps.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
public class DriverInitialization {
    static WebDriverWait  wait;
    public static WebDriver driver ;
    //Sets up and configures the Chrome WebDriver with custom options and preferences
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
    //Initializes the WebDriver before each test scenario
    @Before
    public void setupScenario() {
        driver = setupWebDriver();
        open_the_swag_labs_website();
    }
    //Quits the WebDriver after each test scenario
    @After
    public void teardownScenario() {
        if (driver != null) {
            driver.quit();
        }
    }
    //Opens the Swag Labs website and maximizes the browser window and to ensure the browser is navigated to the correct starting URL
    public void open_the_swag_labs_website() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/index.html");
    }
}
