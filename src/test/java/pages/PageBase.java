package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by KPR on 27/02/2017.
 */
public class PageBase {

        protected WebDriver driver;
        protected WebDriverWait wait;

        public PageBase(WebDriver driver)
        {
            this.driver = driver;
            wait = new WebDriverWait(driver, 10);
        }
}
