import objects.Cart;
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

/*
 * Created by KPR on 24/02/2017.
 Переделайте созданный в задании 13 сценарий для добавления товаров в корзину и удаления товаров из корзины,
 чтобы он использовал многослойную архитектуру.

 А именно, выделите вспомогательные классы
 - для работы с главной страницей (откуда выбирается товар),
 - для работы со страницей товара (откуда происходит добавление товара в корзину),
 - со страницей корзины (откуда происходит удаление),
 и реализуйте сценарий, который не напрямую обращается к операциям Selenium, а оперирует вышеперечисленными объектами-страницами.
 */
public class task19 extends TestBase
{

    @Test
    public void buyGoods() {

        for (int i = 1; i <= 3; i++)
        {
            cart.chooseaGood();
            cart.addtocart();
        }

        cart.checkoutPage();
        cart.removefromCart();

           // System.out.println("blablabla");

    }

}
