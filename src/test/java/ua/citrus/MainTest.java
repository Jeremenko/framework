package ua.citrus;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MainTest extends Settings {


    @Test
    public void searchOnCitrus() {

        WebElement searchField = driver.findElement(By.xpath("//input[@id='search-input']"));
        searchField.click();
        searchField.sendKeys("Xiaomi");
        searchField.sendKeys(Keys.RETURN);
    }


}
