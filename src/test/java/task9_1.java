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
 * 1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
 * а) проверить, что страны расположены в алфавитном порядке
 * б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
 */


public class task9_1 {
    private WebDriver driver;

    @Before
    public void Start()
    {
    driver = new ChromeDriver();
    }

    @Test
    public void test()
    {
        // open adminpage
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        // open "Countries" page
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        // creating a list of rows from datatable
        List<WebElement> elements = driver.findElements(By.cssSelector(".dataTable .row"));
        List<String> countries = new ArrayList<String>();
        List<String> countrieswithzones = new ArrayList<>();
        // Fiiling of lists countries and countrieswithtimezones
        for (WebElement el : elements)
        {
                countries.add(el.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("text"));
                if (!(el.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent")).equals("0"))
                {
                    countrieswithzones.add(el.findElement(By.cssSelector("a")).getAttribute("innerText"));
                }
        }
        //matching if list of countries sorted
        List<String> copy = countries;
        Collections.sort(countries);
        Assert.assertEquals(copy,countries);
        System.out.println("testA done");

        //starting with timezones
        for (String c : countrieswithzones)
        {
            driver.findElement(By.linkText(c)).click();
            //creating a list of timezones
            elements = driver.findElements(By.cssSelector(".dataTable tr td:nth-child(3)"));
            List<String> zones = new ArrayList<>();
            for (WebElement z: elements)
            {
                zones.add(z.getAttribute("textContent"));
            }
            //matching if list of timezones sorted
            copy = zones;
            Collections.sort(zones);
            Assert.assertEquals(copy,zones);
            //back to countries page
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        }
        System.out.println("testB done");
    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}
