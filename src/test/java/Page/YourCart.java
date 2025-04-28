package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YourCart {
    WebDriver driver;
    public  YourCart(WebDriver driver){
        this.driver=driver;
    }
    public WebElement removeSauceLabsBoltTShirt(){
        return driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/button"));
    }
    public WebElement cartPageTitle(){
        return driver.findElement(By.xpath("//*[@id=\"contents_wrapper\"]/div[2]"));//

    }
    public WebElement checkoutButton(){
        return driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]"));//

    }
}
