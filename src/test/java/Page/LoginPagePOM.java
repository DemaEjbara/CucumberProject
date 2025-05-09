package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPagePOM {
    WebDriver driver;

    public LoginPagePOM(WebDriver driver){
        this.driver=driver;
    }
    public WebElement userName(){
        return driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
    }
    public WebElement passWord(){
        return driver.findElement(By.xpath("//*[@id=\"password\"]"));
    }
    public WebElement loginButton(){
        return driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
    }

}
