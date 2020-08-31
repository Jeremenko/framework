package ua.citrus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainTest extends Settings {

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);

    }



    @Test
    public void searchPhones() throws InterruptedException {
        loginToTheCitrus("komarik100@ukr.net", "25121984");

        List<WebElement> elementBaner = driver.findElements(By.xpath("//button[@class='el-dialog__headerbtn bf-video-btn']"));
        if (!elementBaner.isEmpty())
            elementBaner.get(0).click();

        WebElement linkPhones = driver.findElement(By.xpath("//li/a[@title='Смартфоны']"));


        Actions phoneType = new Actions(driver);
        phoneType.moveToElement(linkPhones).build().perform();

        WebElement xiaomiPhones = driver.findElement(By.xpath("//li[@class='menu-aim__item menu-aim__item--active']//span[text()='Xiaomi']"));
        xiaomiPhones.click();

        WebElement xiaomiPhonesPage = driver.findElement(By.xpath("//h1[contains(text(),'Xiaomi')]"));
        assertEquals(xiaomiPhonesPage.getText(), "Смартфоны Xiaomi", "You are in different page");

        WebElement phoneWithMaxPrice = null;
        int maxPrice = 0;
        List<WebElement> allItems = driver.findElements(By.xpath("//div[@class='product-card__overview'][.//div[@class='product-card__footer']//button[@class='product-card__to-basket']]"));
        for (WebElement item : allItems) {
            List<WebElement> phonePrice = item.findElements(By.xpath(".//div[@class='prices__price']//span[@class='price']"));

            if (!phonePrice.isEmpty()) {
                String deleteSpaceInPrice = phonePrice.get(0).getText().replace(" ", "");
                int pricePhoneInt = Integer.parseInt(deleteSpaceInPrice);

                if (maxPrice < pricePhoneInt) {
                    maxPrice = pricePhoneInt;
                    phoneWithMaxPrice = item;
                }
            }
        }
        WebElement basket = phoneWithMaxPrice.findElement(By.xpath(".//div[@class='product-card__footer']//button[@class='product-card__to-basket']"));
        basket.click();
        Thread.sleep(20000);

    }

    private void loginToTheCitrus(String username, String password) throws InterruptedException {
        WebElement login = driver.findElement(By.xpath("//div[@class='name']"));
        login.click();
        WebElement pageLogin = driver.findElement(By.xpath("//h2[@class='identity__title']"));
        assertEquals(pageLogin.getText(), "Вход");
        WebElement loginByEmailButton = driver.findElement(By.xpath("//button[@class='custom-button custom-button custom-button--secondary identity__switch']"));

        loginByEmailButton.click();

        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailField.click();
        emailField.sendKeys(username);

        WebElement buttonNext = driver.findElement(By.xpath("//button[@class='custom-button custom-button custom-button--primary identity__submit']"));
        assertTrue(buttonNext.isEnabled(), "Button Далее is not enabled");
        buttonNext.click();

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder='Пароль']"));
        passField.click();
        passField.sendKeys(password);

        WebElement passButton = driver.findElement(By.xpath("//button[@class='custom-button custom-button--primary email-login__submit']"));
        passButton.click();
        Thread.sleep(10000);

        String helloMe = driver.findElement(By.xpath("//div[@class='name']")).getText();
        assertTrue(helloMe.contains("alex test"), "Alex you are not logged to the Citrus");
    }

}


