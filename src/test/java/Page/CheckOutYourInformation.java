package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutYourInformation {
    WebDriver driver;
    public CheckOutYourInformation(WebDriver driver){
        this.driver=driver;
    }
    public WebElement firstnameField(){
        return driver.findElement(By.xpath("//*[@id=\"first-name\"]"));
    }
    public WebElement lastnameField(){
        return driver.findElement(By.xpath("//*[@id=\"last-name\"]"));
    }
    public WebElement postalCodeField(){
        return driver.findElement(By.xpath("//*[@id=\"postal-code\"]"));
    }
    public WebElement continueButton(){
        return driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input"));
    }
    public WebElement errorButtonIsDisplayed(){
        return driver.findElement(By.className("error-button"));
    }
    public WebElement pageTitle(){
        return driver.findElement(By.xpath("//*[@id=\"contents_wrapper\"]/div[2 and contains(text(), 'Checkout: Your Information')]')]"));
    }


}
