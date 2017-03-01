package objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/*
 * Created by KPR on 27/02/2017.
 */
public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    public CheckoutPage()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
    }

    public void quit() {
        driver.quit();
    }



    public CheckoutPage removefromCart()
    {
        List<WebElement> row = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr td.item"));
        for (int i = 1; i <= row.size(); i++)
            {
                List<WebElement> shortcuts = driver.findElements(By.cssSelector("#box-checkout-checkoutPage .shortcuts li"));
                if (shortcuts.size() > 0)
                {
                    WebElement shortcut = driver.findElement(By.cssSelector("#box-checkout-checkoutPage .shortcuts li:nth-child(1)"));
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

        //wait.until(numberOfElementsToBe(By.cssSelector("#checkout-checkoutPage-wrapper p"), 2));


        return this;

    }

    public boolean emptyCart()
    {
        return driver.findElement(By.cssSelector("#checkout-checkoutPage-wrapper p")).getText().equals("There are no items in your checkoutPage.");
    }

}
