package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutComplete {
    WebDriver driver ;
    public CheckOutComplete(WebDriver driver){
        this.driver=driver;
    }
    public String pageTitle(){
        return driver.findElement(By.xpath("//*[@id=\"contents_wrapper\"]/div[2 and contains(text(), 'Finish')]")).getText();//
    }
    public String thankYouMessage(){
        return driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();
    }
}
