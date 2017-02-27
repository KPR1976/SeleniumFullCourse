package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

/**
 * Created by KPR on 27/02/2017.
 */
public class ProductPage extends PageBase {

    WebElement addbtn = driver.findElement(By.cssSelector(".quantity button"));


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addtocart()
    {
        String qb = driver.findElement(By.cssSelector("#cart .quantity")).getAttribute("textContent");
        if (driver.findElement(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
            new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByValue("Large");
        // add the duck to cart
        addbtn.click();
        // wait for renew of quantity in cart
        wait.until(not(textToBePresentInElementLocated(By.cssSelector("#cart .quantity"),qb)));
    }
}
