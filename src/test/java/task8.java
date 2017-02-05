import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by KPR on 03/02/2017.
 * Сделайте сценарий, проверяющий наличие стикеров у товаров
 */


public class task8 {
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

        // creating a list of products on mainpage
        List<WebElement> elements = driver.findElements(By.cssSelector(".product"));

        // checking of each elemnt have only one sticker
        for (WebElement el :elements)
        {
            Assert.assertTrue(el.findElements(By.cssSelector(".sticker")).size() == 1);
        }
    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}
