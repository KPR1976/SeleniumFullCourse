import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;


/*
 * Created by KPR on 06/02/2017.
 * 2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
 * зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
 */


public class task9_2 {
    private WebDriver driver;

    @Before
    public void Start()
    {
    driver = new ChromeDriver();
    }

    @Test
    public void test()
    {
        // open mainpage of shop
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        // creating a list of countries with geozones
        List<WebElement> elements = driver.findElements(By.cssSelector(".dataTable .row td:nth-child(3)"));
        ArrayList<String> countries = new ArrayList<String>();
        for (WebElement el : elements)
        {
                countries.add(el.getAttribute("innerText"));
        }

        // working with each country
        for (String c : countries)
        {
            driver.findElement(By.linkText(c)).click();
            // creating a list of checked geozones
            elements = driver.findElements(By.cssSelector(".dataTable tr td:nth-child(3) select option:checked"));
            List<String> geozones = new ArrayList<>();
            for (WebElement el : elements)
            {
                geozones.add(el.getAttribute("textContent"));
            }
            //matching if geozones sorted
            List<String> copy = geozones;
            Collections.sort(geozones);
            Assert.assertEquals(copy,geozones);
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}
