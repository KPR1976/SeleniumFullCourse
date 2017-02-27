import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by KPR on 26/02/2017.
 */
public class TestBase
{
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void Start()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public void shopmainPage()
    {
        driver.get("http://localhost/litecart/");
    }

    public void chooseaGood()
    {
        driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1)")).click();
    }

    public void addtoCart(String num)
    {

        if (driver.findElement(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
            new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByValue("Large");
        // add the duck to cart
        driver.findElement(By.cssSelector(".quantity button")).click();
        // wait for renew of quantity in cart
        wait.until(textToBePresentInElementLocated(By.cssSelector("#cart .quantity"),num));
    }

    public void checkoutPage()
    {
        shopmainPage();
        driver.findElement(By.cssSelector("#cart .link")).click();
    }

    public void removefromCart()
    {
        List<WebElement> row = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr td.item"));
        for (int i = 1; i <= row.size(); i++) {

            {
                List<WebElement> shortcuts = driver.findElements(By.cssSelector("#box-checkout-cart .shortcuts li"));
                if (shortcuts.size() > 0) {
                    WebElement shortcut = driver.findElement(By.cssSelector("#box-checkout-cart .shortcuts li:nth-child(1)"));
                    shortcut.click();
                    WebElement btn = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                    btn.click();
                    wait.until(stalenessOf(row.get(0)));
                }
                else
                {
                    WebElement btn = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                    btn.click();
                    wait.until(stalenessOf(row.get(0)));
                }
            }

            wait.until(numberOfElementsToBe(By.cssSelector("#checkout-cart-wrapper p"), 2));
            Assert.assertEquals(driver.findElement(By.cssSelector("#checkout-cart-wrapper p")).getText(),
                    "There are no items in your cart.");

        }
    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}
