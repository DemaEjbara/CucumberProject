package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class CheckOutYourInformation {
    WebDriver driver;
    public CheckOutYourInformation(WebDriver driver){
        this.driver=driver;
    }
    public WebElement errorButtonIsDisplayed(){
        return driver.findElement(By.className("error-button"));
    }

}
