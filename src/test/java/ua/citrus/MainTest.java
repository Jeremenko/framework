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
        assertTrue(buttonNext.isEnabled(), "Button Далее is not enabled");
        buttonNext.click();

        WebElement passField = driver.findElement(By.xpath("//input[@placeholder='Пароль']"));
        passField.click();
        passField.sendKeys("25121984");
        WebElement passButton = driver.findElement(By.xpath("//button[@class='custom-button custom-button--primary email-login__submit']"));
        passButton.click();
        Thread.sleep(10000); //вот если тут не ставить ожидание то падает.... как по человечески это сделать ??

        String helloMe = driver.findElement(By.xpath("//div[@class='name']")).getText();
        assertTrue(helloMe.contains("alex test"), "Alex you are not logged to the Citrus");

        List<WebElement> elementBaner = driver.findElements(By.xpath("//button[@class='el-dialog__headerbtn bf-video-btn']"));
        if (!elementBaner.isEmpty())
            elementBaner.get(0).click();

        WebElement linkPhones = driver.findElement(By.xpath("//li/a[@title='Смартфоны']"));

        Actions phoneType = new Actions(driver);
        phoneType.moveToElement(linkPhones).build().perform();

// Может можно как то написать адекватный Xpath ????
// li[@class='menu-aim__item menu-aim__item--active']//div[@class='wrap']//div[1]//ul[1]//li[5]//a[1]//span[1]

        WebElement xiaomiPhones = driver.findElement(By.xpath(
                "//li[@class='menu-aim__item menu-aim__item--active']//div[@class='wrap']//div[1]//ul[1]//li[5]//a[1]//span[1]"));
        xiaomiPhones.click();
        WebElement xiaomiPhonesPage = driver.findElement(By.xpath("//h1[contains(text(),'Xiaomi')]"));

        assertEquals(xiaomiPhonesPage.getText(), "Смартфоны Xiaomi", "You are in different page");

        List<WebElement> phonesPrice = driver.findElements(By.xpath("//div[@class='prices__price']//span[@class='price']"));
        //  System.out.println(phonesPrice.size());
        WebElement phoneWithMaxPrice = null;
        int maxPrice = 0;
        for (WebElement phonePrice : phonesPrice) {
            String string = phonePrice.getText().replace(" ", "");

            int priceInt = Integer.parseInt(string); //выводит список прайсов как цыфры и без пробелов готов к сравнению
            //System.out.println(priceInt);
            if (maxPrice < priceInt ) {
                maxPrice = priceInt;
                phoneWithMaxPrice = phonePrice;
            }

        }
        System.out.println(maxPrice);

//        String xPath = "//div[@class='product-card__overview']/div[.//span[@class='price' and text()='4 999']]//div[@class='product-card__name']";
//        WebElement phoneOverview = driver.findElement(By.xpath(String.format(xPath, maxPrice)));

//        for (int i = 0; i < phonesPrice.size(); i++) { // Как сделать работающий if для этого for
//            if (maxPrice < phonesPrice.get(i).getText()) ; //ищем большую цену, и записываем ее в переменную maxPrice
//            System.out.println(i + "  " + phonesPrice.get(i).getText());
//        }

        //после нахождения найбольшего прайса что делать дальше как его добавить в корзину

    }


}


