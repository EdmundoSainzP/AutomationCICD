package org.edspDeloitte;

import org.edspDeloitte.PageObjects.CartPage;
import org.edspDeloitte.PageObjects.ProductPage;
import org.edspDeloitte.TestComponents.BaseTest;
import org.edspDeloitte.TestComponents.Retry;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {
    @Test (groups = {"ErrorValidation"}, retryAnalyzer = Retry.class)
    public void wrongCredentialsLogin() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";
        lp.loginAction("esainzpalacios@gmail.com","test");
        Assert.assertEquals(lp.getErrorMessage(),"Incorrect email or password.@");

    }
    @Test (groups = {"ErrorValidation"})
    public void productErrorValidation() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        //Product Page
        ProductPage pp = lp.loginAction("esainzpalacios@gmail.com", "Yo@nofui31");
        pp.addProductToCart(productName);
        //Cart page
        CartPage cp = pp.goToCartPage();
        Assert.assertFalse(cp.getProductCartList().stream().anyMatch(
                e -> e.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase("ZARA COAT 33")));
    }
}
