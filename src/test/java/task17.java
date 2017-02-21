import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        private WebDriverWait wait;

        @Before
        public void Start()
        {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }

        @Test
        public void test ()
        {
            driver.get("http://localhost/litecart/admin");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();

            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

            //List<WebElement> goods = driver.findElements(By.partialLinkText("Duck"));
            //List<WebElement> goods = driver.findElements(By.cssSelector(".dataTable .row .td[3] a"));
            List<WebElement> goods = driver.findElements(By.xpath(".//tr/td[3]/a"));
            System.out.println(goods.size());
            for (WebElement g : goods)
            {
                System.out.println(g.getText());
                if (!g.getText().equals("Subcategory"))
                {
                    //System.out.println(g.getText());
                    g.click();
                    System.out.println("dg");
                    for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                    System.out.println(l);
                    }
                    System.out.println("dgg");
                }
                driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            }

            System.out.println("blablabla");

        }

        @After
        public void stop()
        {
            driver.quit();
            driver = null;
        }
}
