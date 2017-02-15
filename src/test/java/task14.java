import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
//import static org.openqa.selenium.support.ui.ExpectedConditions.;


import java.util.List;
import java.util.Set;

/*
 Created by KPR on 15/02/2017.
 Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.
 Сценарий должен состоять из следующих частей:
 1) зайти в админку
 2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
 3) открыть на редактирование какую-нибудь страну или начать создание новой
 4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
 Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank". Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне, потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.
 */
public class task14 {
    private WebDriver driver;
    private WebDriverWait wait;


    private String checkNewWindow(Set<String> oldWindows)
    {
        Set<String> windows = driver.getWindowHandles();
        windows.removeAll(oldWindows);
        if (windows.size() > 0)
            return windows.iterator().next();
        else
            return null;

    } //*/

    @Before
    public void Start()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }



    @Test
    public void myTest()
    {
        // go to admin page and authenticate
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // go to countries page
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //click on "add new country"
        driver.findElement(By.cssSelector(".button")).click();
        List<WebElement> externallinks = driver.findElements(By.cssSelector("form tbody tr td [target='_blank']"));
        System.out.println(externallinks.size());

        for (WebElement externallink : externallinks)
        {
            String current = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            externallink.click();
            String newWindow = checkNewWindow(oldWindows);
            driver.switchTo().window(newWindow);
            System.out.println(driver.getTitle());
            driver.close();
            driver.switchTo().window(current);
        }

        System.out.println("blablabla");
    }




    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }
}
