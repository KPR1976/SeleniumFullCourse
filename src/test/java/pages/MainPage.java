package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by KPR on 27/02/2017.
 */
public class MainPage extends PageBase
{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open()
    {
        driver.get("http://localhost/litecart/");
        return this;
    }
}
