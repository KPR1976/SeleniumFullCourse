import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 * Created by KPR on 12/02/2017.
 */
public class task12 {
    private WebDriver driver;
    private WebDriverWait wait;

    private boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }

    @Before
    public void Start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void test() {
        // open adminpage
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        // go to catalog
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        // click on "Add New Product"
        driver.findElement(By.cssSelector("#content .button:nth-child(2)")).click();

        // fill "General" page
        // Change status to "Enabled"
        driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        // fill the name and code
        String name = "Diver Duck";
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(name);
        String code = "dd1";
        driver.findElement(By.cssSelector("[name=code]")).sendKeys(code);
        // check a category "Rubber Ducks"
        driver.findElement(By.cssSelector("[name='categories[]'][value='0']")).click();
        driver.findElement(By.cssSelector("[name='categories[]'][value='1']")).click();
        // check a product group
       driver.findElement(By.cssSelector("[name='product_groups[]'][value='1-3']")).click();
        // fill quantity
        String quantity = "20";
        driver.findElement(By.cssSelector("[name = 'quantity']")).clear();
        driver.findElement(By.cssSelector("[name = 'quantity']")).sendKeys(quantity);
        // add a image of new product with absolute path
        File ddpic = new File("src/test/java/diver duck.jpg");
        String filepath = ddpic.getAbsolutePath();
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(filepath);
        driver.findElement(By.cssSelector("#add-new-image")).click();
        // fill a dates date from and dateto
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd/MM/yyyy" );
        LocalDate datefrom = LocalDate.now();
        LocalDate dateto = LocalDate.now().plusYears ( 1 );
        String sdatefrom = datefrom.format(formatter);
        String sdateto = dateto.format(formatter);
        driver.findElement(By.cssSelector("[name='date_valid_from']")).sendKeys(sdatefrom);
        driver.findElement(By.cssSelector("[name='date_valid_to']")).sendKeys(sdateto);

        // go to "Information" page
        driver.findElement(By.cssSelector(".index li:nth-child(2)")).click();
        // select a manyfacturer
        new Select(driver.findElement(By.cssSelector("[name='manufacturer_id']"))).selectByVisibleText("ACME Corp.");
        // fill a text boxes
        String keyword = "diver";
        driver.findElement(By.cssSelector("[name='keywords']")).sendKeys(keyword);
        String shortdescription = "for test";
        driver.findElement(By.cssSelector("[name='short_description[en]'")).sendKeys(shortdescription);
        String description = "A diver duck - for test";
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(description);
        String head_title = "Diver Duck";
        driver.findElement(By.cssSelector("[name='head_title[en]'")).sendKeys(head_title);
        String meta_desc = "Meta_description text";
        driver.findElement(By.cssSelector("[name='meta_description[en]'")).sendKeys(meta_desc);

        // go to "Prices" page
        driver.findElement(By.cssSelector(".index li:nth-child(4)")).click();
        // fill purchase price
        String price = "25.5";
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).clear();
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).sendKeys(price);
        // select a purchase currency
        new Select(driver.findElement(By.cssSelector("[name='purchase_price_currency_code']"))).selectByValue("EUR");
        // fill a price of product
        price = "40";
        driver.findElement(By.cssSelector("[name = 'prices[USD]']")).clear();
        driver.findElement(By.cssSelector("[name = 'prices[USD]']")).sendKeys(price);

        // save a data
        driver.findElement(By.cssSelector("[name = save]")).click();

        // check if product is added
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        Assert.assertTrue(isElementPresent(By.linkText(name)));

    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}
