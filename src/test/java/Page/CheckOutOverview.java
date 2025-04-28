package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutOverview {
    WebDriver driver;

    public CheckOutOverview(WebDriver driver){
        this.driver =driver;
    }
    public WebElement pageTitle(){
        return driver.findElement(By.xpath("//*[@id=\"contents_wrapper\"]/div[2 and contains(text(), 'Checkout: Overview')]"));
    }
    public String pageCurrentUrl(){
        return driver.getCurrentUrl();
    }
    public WebElement totalPrice(){
        return  driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]"));
    }
    public WebElement finish(){
        return driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]"));
    }
}
