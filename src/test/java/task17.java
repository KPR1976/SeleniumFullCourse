import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KPR on 21/02/2017.
Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения при открытии страниц в учебном приложении, а именно -- страниц товаров в каталоге в административной панели.
 Сценарий должен состоять из следующих частей:
 1) зайти в админку
 2) открыть каталог, категорию, которая содержит товары (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
 3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
 */
public class task17
{
        private WebDriver driver;

        @Before
        public void Start()
        {
            driver = new ChromeDriver();
        }

        @Test
        public void test ()
        {
            // login to admin page
            driver.get("http://localhost/litecart/admin");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();

            // go to goods catalog
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

            // list of webelements for goods
            //List<WebElement> goods = driver.findElements(By.cssSelector(".dataTable .row .td[3] a")); cssselector
            List<WebElement> goodsw = driver.findElements(By.xpath(".//tr/td[3]/a")); //xpath
            List<String> goods = new ArrayList<>();
            // check every webelement if it product or category
            for (WebElement g : goodsw)
            {
                if (!g.getText().equals("Subcategory"))
                    goods.add(g.getAttribute("textContent"));
            }

            // go to the good's page
            for (String g : goods)
            {
                driver.findElement(By.linkText(g)).click();
                // check browserlogs for page
                Assert.assertTrue(driver.manage().logs().get("browser").getAll().size() == 0);
                driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            }

        }

        @After
        public void stop()
        {
            driver.quit();
            driver = null;
        }
}
