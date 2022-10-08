package tests;

/*
case 1: успешная регистрация на сайте с заполнением только обязательных полей
case 2: успешная регистрация на сейте с заполнением всех полей в форме регистрации
case 3: заполнить не все обязательные поля при регитрации
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ShareLaneTests {

    WebDriver driver;

    @Test
    public void successfulRegistrationMandatoryFieldsTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name='zip_code']")).sendKeys("11111");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Anna");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("anna@gmail.com");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        String actualText = driver.findElement(By.xpath("//span[@class='confirmation_message']")).getText();
        Assert.assertEquals(actualText,"Account is created!");
    }

    @Test
    public void successfulRegistrationAllFieldsTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name='zip_code']")).sendKeys("11111");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Anna");
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Yakshevich");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("anna@gmail.com");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        String actualText = driver.findElement(By.xpath("//span[@class='confirmation_message']")).getText();
        Assert.assertEquals(actualText,"Account is created!");
    }

    @Test
    public void negativeRegistrationFillNotAllMandatoryFieldsTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name='zip_code']")).sendKeys("11111");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Yakshevich");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("anna@gmail.com");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        String actualText = driver.findElement(By.xpath("//span[@class='error_message']")).getText();
        Assert.assertEquals(actualText,"Oops, error on page. Some of your fields have invalid data or " +
                "email was previously used");
    }
    
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
