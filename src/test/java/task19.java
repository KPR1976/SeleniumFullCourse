import pages.CheckoutPage;
import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;
import pages.ProductPage;

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
    public void test()
    {
        MainPage shoppage  = new MainPage(driver);
        ProductPage productpage = new ProductPage(driver);
        CheckoutPage checkoutpage = new CheckoutPage(driver);


        for (int i = 1; i <= 3; i++)
        {
            shoppage.open();
            productpage = shoppage.chooseProduct();
            productpage.addtocart();
        }

       checkoutpage = productpage.gotoCheckout();
       checkoutpage.removefromCart();

       Assert.assertTrue(checkoutpage.emptyCart());

    }

}
