import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


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

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }
}

