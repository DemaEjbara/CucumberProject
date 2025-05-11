package HelpersUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
public class locatorUtil {
    /**
     * Static block to initialize the Properties object by loading the "locators.properties" file.
     * This block reads the properties file located in "src/test/resources/locators.properties" and loads
     * the contents into the {properties} object.
     * If the file cannot be loaded, an exception is thrown, which will terminate the test setup.
     */
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
    /**
     * Retrieves a locator (XPath) from the properties file using the provided key.
     * This method fetches the value corresponding to the given key from the "locators.properties" file
     * and returns it as a {By} XPath locator. If the locator is not found or is empty, it throws
     * an {IllegalArgumentException}.
     * @param key the key associated with the XPath in the properties file
     * @return the {By} XPath locator corresponding to the provided key
     * @throws IllegalArgumentException if no locator is found for the provided key
     */
    public static By getLocater (String key){
        String xpath =properties.getProperty(key);
        if(xpath==null || xpath.trim().isEmpty()){
            throw new IllegalArgumentException("no locator found for key : "+key);
        }
        return By.xpath(xpath);
    }
    /**
     * Retrieves a text value associated with a key from the properties file.
     * This method retrieves the value corresponding to the key with ".text" suffix in the properties file.
     * It throws an { IllegalArgumentException} if the text is not found or is empty.
     * @param key the key associated with the text value in the properties file
     * @return the text value corresponding to the provided key
     * @throws IllegalArgumentException if no text value is found for the provided key
     */
    public static String getText(String key) {
        String text = properties.getProperty(key + ".text");
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("No text value found for key: " + key + ".text");
        }
        return text.trim();
    }
    /**
     * Retrieves a locator (XPath) from the properties file using the provided key with a ".locator" suffix.
     * This method fetches the value corresponding to the given key with ".locator" suffix in the "locators.properties"
     * file and returns it as a { By} XPath locator. If no locator is found or the value is empty, it throws an
     * {IllegalArgumentException}.
     * @param key the key associated with the XPath in the properties file (with ".locator" suffix)
     * @return the {By} XPath locator corresponding to the provided key
     * @throws IllegalArgumentException if no locator is found for the provided key
     */
    public static By GetLocater(String key) {
        String locatorValue = properties.getProperty(key + ".locator");
        if (locatorValue == null || locatorValue.trim().isEmpty()) {
            throw new IllegalArgumentException("No locator found for key : " + key);
        }
        return By.xpath(locatorValue.trim());
    }

}
