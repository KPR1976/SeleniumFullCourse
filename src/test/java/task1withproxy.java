import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by KPR on 26/02/2017.
 */
public class task1withproxy {
    private WebDriver driver;
    private WebDriverWait wait;
    public BrowserMobProxy proxy;

    @Before
    public void Start()
    {
        proxy = new BrowserMobProxyServer();
        proxy.start(8080);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        //proxy.setHttpProxy("localhost:8888");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("proxy", seleniumProxy);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        caps.setCapability(ChromeOptions.CAPABILITY,options);
        driver = new ChromeDriver(caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test ()
    {
        proxy.newHar();
        driver.get("http://www.google.ru");
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("selenium - Поиск в Google"));
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
    }

    @After
    public void stop()
    {
        driver.quit();
        driver = null;
    }
}
