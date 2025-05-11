package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
    }
    public  String pageUrl(){
        return driver.getCurrentUrl();
    }
    public WebElement nameAtoZ(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[1]"));
    }
    public WebElement nameZtoA(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[2]"));
    }
    public WebElement nameLowToHigh(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[3]"));
    }
    public WebElement nameHighToLow(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[4]"));
    }
    public List<WebElement> buttonElementList() {
        return driver.findElements(By.xpath("//button[@class='btn_primary btn_inventory']"));
    }
    public List<WebElement> nameOfElements(){
        return driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
    }
    public List<WebElement> itemOfElements(){
        return driver.findElements(By.xpath("//div[@class='inventory_item']"));
    }
    public  WebElement removeSauceLabsFleeceJacket(){
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']//parent::a//parent::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']//child::button[text()='REMOVE']"));
    }
    public WebElement cartnumber(){
        return driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a//span[contains(@class,'fa-layers-counter shopping_cart_badge')]"));

    }
}
