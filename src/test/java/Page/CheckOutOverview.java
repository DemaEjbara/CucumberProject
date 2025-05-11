package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class CheckOutOverview {
    WebDriver driver;
    public CheckOutOverview(WebDriver driver){
        this.driver =driver;
    }
    public WebElement totalPrice(){
        return  driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]"));
    }
}
