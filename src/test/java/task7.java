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
 * Created by KPR on 02/02/2017.
 * Сделайте сценарий, проходящий по всем разделам админки
 */


public class task7 {
    private WebDriver driver;
    // method for check if elements exist
    private boolean areElementsPresent(WebDriver driver, By locator)
    {
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void Start()
    {
    driver = new ChromeDriver();
    }

    @Test
    public void myTest() throws Exception
    {
        // login to admin console
        // TO DO create external class for future tasks
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        // creating of list with menu elements
        List<WebElement> menuelements = driver.findElements(By.cssSelector("li#app-"));
        // loop on list for click and find a child elements
        for (int i = 1; i <= menuelements.size(); i++)
        {
            // click on element link
            driver.findElement(By.cssSelector("li#app-:nth-child("+ i +") a")).click();
            //assert on "h1" tag
            Assert.assertTrue(areElementsPresent(driver, By.cssSelector("h1")));
            //crating of list with child elements
            List<WebElement> childelements = driver.findElements(By.cssSelector("li[id^=doc]"));
            // checking how much childelements exist
            if (childelements.size() > 0)
            {
                //loop on list with child elements
                for (int j = 1; j <= childelements.size(); j++)
                {
                    //click on child element
                    driver.findElement(By.cssSelector("li[id^=doc]:nth-child("+ j +") a")).click();
                    //assert on "h1" tag
                    Assert.assertTrue(areElementsPresent(driver,By.cssSelector("h1")));
                }
            }
        }
    }


    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}
