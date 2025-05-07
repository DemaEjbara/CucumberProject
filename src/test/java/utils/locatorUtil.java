package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
public class locatorUtil {
    private static final Properties properties =new Properties();
    static {
        try{
            FileInputStream fis =new FileInputStream("src/test/resources/locators.properties");
            properties.load(fis);
        }
        catch (IOException e){
         e.printStackTrace();
         throw new RuntimeException("the properties file can not be loaded");
        }
    }
    public static By getLocater (String key){
        String xpath =properties.getProperty(key);
        if(xpath==null || xpath.trim().isEmpty()){
            throw new IllegalArgumentException("no locator found for key : "+key);
        }
        return By.xpath(xpath);
    }
}
