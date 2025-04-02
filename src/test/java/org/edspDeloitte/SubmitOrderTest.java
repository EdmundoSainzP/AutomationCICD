package org.edspDeloitte;

import org.edspDeloitte.PageObjects.*;
import org.edspDeloitte.TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    //New comments
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups ={"Purchase"})
    //1 solution: public void createOrder(String email, String pass, String prodName) throws InterruptedException, IOException {
    public void createOrder(HashMap<String, String> i) throws InterruptedException, IOException {
        //Product Page
        ProductPage pp = lp.loginAction(i.get("email"),i.get("pass"));
        pp.addProductToCart(i.get("prodName"));
        //Cart page
        CartPage cp = pp.goToCartPage();
        System.out.println();
        Assert.assertTrue(cp.getProductCartList().stream().anyMatch(
                e->e.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(i.get("prodName"))));
        //checkout page
        CheckoutPage chp = cp.goToCheckout();
        chp.setExpiryDate("12","31");
        chp.setCvv("345");
        chp.setCardName("Edmundo Sanchez");
        chp.selectCountry("mexico");
        //Confirmation page
        ConfirmationPage cop=chp.placeOrder();
        //System.out.println(cop.getConfirMessTitle());
        Assert.assertTrue(cop.getConfirMessTitle().equalsIgnoreCase("Thankyou for the order."));
    }
    //TO verify Zara coat 3 is displaying in orders page
    @Test (dependsOnMethods = {"createOrder"})
    public void orderHistoryTest(){
        ProductPage pp = lp.loginAction("esainzpalacios@gmail.com","Yo@nofui31");
        OrdersPage op = pp.goToOrdersPage();
        Assert.assertTrue(op.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //solution 1
        // return new Object[][]{{"esainzpalacios@gmail.com", "Yo@nofui31", "ZARA COAT 3"}, {"esainzpalacios@gmail.com", "Yo@nofui31", "ADIDAS ORIGINAL"}, {"esainzpalacios@gmail.com", "Yo@nofui31", "IPHONE 13 PRO"}};
        //solution 2
//        HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//        map1.put("email","esainzpalacios@gmail.com");
//        map1.put("pass","Yo@nofui31");
//        map1.put("prodName","ZARA COAT 3");
//        HashMap<Object,Object> map2 = new HashMap<Object,Object>();
//        map2.put("email","esainzpalacios@gmail.com");
//        map2.put("pass","Yo@nofui31");
//        map2.put("prodName","ADIDAS ORIGINAL");
//        HashMap<Object,Object> map3 = new HashMap<Object,Object>();
//        map3.put("email","esainzpalacios@gmail.com");
//        map3.put("pass","Yo@nofui31");
//        map3.put("prodName","IPHONE 13 PRO");
//        return new Object[][]{
//                {map1},
//                {map2},
//                {map3}
//                };
        //solution 3 Data source file, but could be DB or anything
        List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty(("user.dir"))+"/src/test/java/org/edspDeloitte/data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)}
                , {data.get(1)}
                , {data.get(2)}
        };
    }
}
