import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

/**
 * Created by KPR on 08/02/2017.
 * Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart
 */


public class task11 {
    private WebDriver driver;

    @Before
    public void Start()
    {
    driver = new ChromeDriver();
    }

    @Test
    public void myTest() throws Exception
    {
        // open mainpage of shop
        driver.get("http://localhost/litecart/");

        // clicking on create a new user
        WebElement newuserlink = driver.findElement(By.cssSelector("#box-account-login td a"));
        newuserlink.click();

        // create random char for difference emails
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');

        // data for create a new user
        String firstName = "Name";
        String lastName = "Lastname";
        String address = "5th Avenue 5";
        String postcode = "12345";
        String city = "New York";
        String country = "United States";
        String email = "user" + c + "@gmail.com";
        String phone = "1234567";
        String password = "pass1word";

        WebElement createaccount = driver.findElement(By.cssSelector("#create-account .content"));
        createaccount.findElement(By.cssSelector("[name=firstname]")).sendKeys(firstName);
        createaccount.findElement(By.cssSelector("[name=lastname]")).sendKeys(lastName);
        createaccount.findElement(By.cssSelector("[name=address1]")).sendKeys(address);
        createaccount.findElement(By.cssSelector("[name=postcode]")).sendKeys(postcode);
        createaccount.findElement(By.cssSelector("[name=city]")).sendKeys(city);
        createaccount.findElement(By.cssSelector("[name=country_code]")).sendKeys(country);
        createaccount.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        createaccount.findElement(By.cssSelector("[name=phone]")).sendKeys(phone);
        createaccount.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        createaccount.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);
        createaccount.findElement(By.cssSelector("button")).click();

        // logout
        driver.findElement(By.cssSelector(".account li:nth-child(5) a")).click();

        //login of new user
        driver.findElement(By.cssSelector("#box-account-login td input:nth-child(3)")).sendKeys(email);
        driver.findElement(By.cssSelector("#box-account-login td input:nth-child(2)")).sendKeys(password);
        driver.findElement(By.cssSelector("#box-account-login td .button-set [name='login']")).click();

    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}
