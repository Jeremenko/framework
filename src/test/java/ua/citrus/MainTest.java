package ua.citrus;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainTest extends Settings {

    public static String removeCharAt(String s, int pos){
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
        Thread.sleep(10000);

        String helloMe = driver.findElement(By.xpath("//div[@class='name']")).getText();
        assertTrue(helloMe.contains("alex test"), "Alex you are not logged to the Citrus");


       if (driver.findElement(By.xpath("//button[@class='el-dialog__headerbtn bf-video-btn']")).isDisplayed())
        driver.findElement(By.xpath("//button[@class='el-dialog__headerbtn bf-video-btn']")).click();


        WebElement linkPhones = driver.findElement(By.xpath("//li/a[@title='Смартфоны']"));


    //    Thread.sleep(3000);

        Actions phoneType = new Actions(driver);
        phoneType.moveToElement(linkPhones).build().perform();

// Может можно как то написать адекватный Xpath
// li[@class='menu-aim__item menu-aim__item--active']//div[@class='wrap']//div[1]//ul[1]//li[5]//a[1]//span[1]

        WebElement xiaomiPhones = driver.findElement(By.xpath(
                "//li[@class='menu-aim__item menu-aim__item--active']//div[@class='wrap']//div[1]//ul[1]//li[5]//a[1]//span[1]"));
        xiaomiPhones.click();
        WebElement xiaomiPhonesPage = driver.findElement(By.xpath("//h1[contains(text(),'Xiaomi')]"));

        assertEquals(xiaomiPhonesPage.getText(), "Смартфоны Xiaomi", "You are in different page");




//        WebElement descPrice = driver.findElement(By.xpath("//div[@class='catalog__tool-bar']//li[3]"));
//        descPrice.click();
//        Thread.sleep(3000);
//
 /*       WebElement clickBasket1 = driver.findElement(By.xpath("(//button[@class='product-card__to-basket'])[1]"));
        clickBasket1.click();
        Thread.sleep(3000);

        WebElement closeBasket = driver.findElement(By.xpath("//i[@class='el-dialog__close el-icon el-icon-close']"));
        closeBasket.click();

        Thread.sleep(3000); */

//        WebElement clickBasket2 = driver.findElement(By.xpath("(//button[@class='product-card__to-basket'])[2]"));
//        clickBasket2.click();
//
//
//       closeBasket.click();





     // List<WebElement> phones = driver.findElements(By.xpath("//div[@class='product-card__prices']"));
//        List<WebElement> phones = driver.findElements(By.xpath("//*[@class='product-card__to-basket']"));
//        System.out.println(phones.size());
//
//        for (int i = 0; i < phones.size(); i++) {
//            System.out.println(i + "  " + phones.get(i).getText());
//        }

        List<WebElement> phonesPrice = driver.findElements(By.xpath("//div[@class='prices__price']//span[@class='price']"));
        System.out.println(phonesPrice.size());
//        phonesPrice.get(1).getText();
        String string = phonesPrice.get(1).getText();

        int indexM = string.indexOf(" ");
        String price_str = removeCharAt(string, indexM);// строка без пробела
        System.out.println(removeCharAt(string, indexM));
        int priceInt = Integer.parseInt(price_str);

        Thread.sleep(3000);

        int maxPrice = 0;

        for (int i = 0; i < phonesPrice.size(); i++) {
            if (maxPrice < phonesPrice.get(i).getText());

            )
            System.out.println(i + "  " + phones.get(i).getText());
        }


//        for (int i=0; i<phones.size()-1; i++)
//        {
//            System.out.println("Елемент" + "[" + i + "] = "+ "[" + phones.get(i).getText()+ "]");
//
//        }











//        WebElement searchField = driver.findElement(By.xpath("//input[@id='search-input']"));
//        searchField.click();
//        searchField.sendKeys("Xiaomi");
//        searchField.sendKeys(Keys.RETURN);
//
//        WebElement searchResults = driver.findElement(By.xpath("//h2[@class='result-title']//span"));
//        assertEquals(searchResults.getText(), "Результаты поиска", "We did not find you phone");
//
//
//         Thread.sleep(3000);


    }


}


