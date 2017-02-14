import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

/**
 * Created by KPR on 14/02/2017.
 Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
 1) открыть главную страницу
 2) открыть первый товар из списка
 2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
 3) подождать, пока счётчик товаров в корзине обновится
 4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
 5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
 6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
 **/
public class task13
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
    public void myTest() throws Exception {
        // open mainpage of shop and add 3 ducks to cart in loop
        for (int i = 1; i <= 3; i++) {
            String num = String.valueOf(i);
            // open mainpage
            driver.get("http://localhost/litecart/");
            // find first item in popular and click
            driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1)")).click();
            // check "Yellow duck" because it contains 3 sizes of duck
            if (driver.findElement(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
                new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByValue("Large");
            // add the ducj to cart
            driver.findElement(By.cssSelector(".quantity button")).click();
            // wait for renew of quantity in cart
            wait.until(textToBePresentInElementLocated(By.cssSelector("#cart .quantity"),num));
        }
        // go to checkout
        driver.findElement(By.cssSelector("#cart .link")).click();
        // count how elements in cart and remove one after one
        List<WebElement> row = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr td.item"));
        for (int i = 0; i < row.size(); i++)
        {

            WebElement removeitem = driver.findElement(By.cssSelector("[value='Remove']"));
            removeitem.click();
            wait.until(stalenessOf(row.get(0)));
        }
    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }
}
