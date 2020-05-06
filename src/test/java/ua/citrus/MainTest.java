package ua.citrus;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainTest extends Settings {


    @Test
    public void searchPhones() throws InterruptedException {
        // login to the Citrus
        WebElement login = driver.findElement(By.xpath("//div[@class='name']"));
        login.click();
        WebElement pageLogin = driver.findElement(By.xpath("//h2[@class='identity__title']"));
        assertEquals(pageLogin.getText(), "вход");
        WebElement loginByEmailButton = driver.findElement(By.xpath("//button[@class='custom-button custom-button custom-button--secondary identity__switch']"));

        loginByEmailButton.click();

        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailField.click();
        emailField.sendKeys("komarik100@ukr.net");

        WebElement buttonNext = driver.findElement(By.xpath("//button[@class='custom-button custom-button custom-button--primary identity__submit']"));

        assertTrue(buttonNext.isEnabled() , "Button Далее is not enabled");

        buttonNext.click();

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder='Пароль']"));
        passField.click();
        passField.sendKeys("25121984");
        WebElement passButton = driver.findElement(By.xpath("//button[@class='custom-button custom-button--primary email-login__submit']"));
        passButton.click();
        Thread.sleep(10000);
        // нужен асерт который будет проверять залогинился ли я


//        WebElement searchField = driver.findElement(By.xpath("//input[@id='search-input']"));
//        searchField.click();
//        searchField.sendKeys("Xiaomi");
//        searchField.sendKeys(Keys.RETURN);
//
//        WebElement searchResults = driver.findElement(By.xpath("//h2[@class='result-title']//span"));
//
//
//        assertEquals(searchResults.getText(), "Результаты поиска1", "We did not find you phone");


        // Thread.sleep(3000);


    }


}


