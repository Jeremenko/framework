package ua.citrus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Settings {

    public static WebDriver driver;


    @BeforeClass
    public void setUP() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        //Create a map to store  preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        //add key and value to map as follow to switch off browser notification
        //Pass the argument 1 to allow and 2 to block
        prefs.put("profile.default_content_setting_values.notifications", 2);
        //Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();
        // set ExperimentalOption - prefs
        options.setExperimentalOption("prefs", prefs);
        //Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://www.citrus.ua/");
    }



    @AfterClass
    public void quitDriver() {
        driver.quit();
    }


}
