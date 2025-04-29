package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver=null;
    public HomePage(WebDriver driver){
        this.driver=driver;
    }
    By passwordPopup=By.id("changePasswordModal");
    By popupCloseButton=By.cssSelector(".close-btn");
    public void closePasswordChangePopupIfPresent (){
        try {
            WebElement popup=driver.findElement(passwordPopup);
            if(popup.isDisplayed()){
                driver.findElement((popupCloseButton)).click();
            }
        }
        catch(Exception e){

        }
    }
    public boolean isOnInventoryPage(){
        return driver.getCurrentUrl().contains("inventory");
    }
    public  String pageUrl(){
        return driver.getCurrentUrl();
    }
    public WebElement sortingDropDown(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select"));
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
    public WebElement FirstProduct(){
        return driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));

    }
    public WebElement SecondProduct(){
        return driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div"));

    }
    public WebElement ThirdProduct(){
        return driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div"));

    }
    public WebElement sauceLabsBackPackAvailable(){
        return driver.findElement(By.xpath("//div[text()=\"Sauce Labs Backpack\"]//parent::a//parent::div"));
    }
    public WebElement sauceLabsBackpackAdd(){
        return driver.findElement(By.xpath("//div[text()=\"Sauce Labs Backpack\"]//parent::a//parent::div//following-sibling::div//following::div[1]//child::button"));

    }

    public WebElement sauceLabsBoltTShirt(){
        return driver.findElement(By.xpath("//div[text()=\"Sauce Labs Bike Light\"]//parent::a//parent::div//following-sibling::div[@class='pricebar']//following::div[1]//child::button"));

    }
    public WebElement sauceLabsBackpackImg(){
        return driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img"));

    }
    public WebElement sauceLabsBikeLightAdd(){
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']//parent::a//parent::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']//child::button"));

    }
    public WebElement sauceLabsBoltTShirtAdd(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[3]/div[3]/button"));

    }
    public WebElement sauceLabsFleeceJacketAdd(){
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']//parent::a//parent::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']//child::button"));

    }
    public  WebElement removeSauceLabsFleeceJacket(){
        return driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']//parent::a//parent::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']//child::button[text()='REMOVE']"));
    }
    public WebElement cartnumber(){
        return driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a//span[contains(@class,'fa-layers-counter shopping_cart_badge')]"));

    }
    public WebElement cartIcon(){
        return driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));

    }

    public WebElement firstProductPrice(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]//div[contains(@class,'inventory_item_price')]"));

    }
    public WebElement secondProductPrice(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/div"));

    }
    public WebElement thirdProductPrice(){
        return driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[3]/div[3]/div"));

    }

}
