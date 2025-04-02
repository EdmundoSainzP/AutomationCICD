package org.edspDeloitte.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.edspDeloitte.PageObjects.CartPage;
import org.edspDeloitte.PageObjects.CheckoutPage;
import org.edspDeloitte.PageObjects.ConfirmationPage;
import org.edspDeloitte.PageObjects.ProductPage;
import org.edspDeloitte.TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest{
    public ProductPage pp;
    public CartPage cp;
    public CheckoutPage chp;
    public ConfirmationPage cop;
    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
        launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String userName, String password){
        pp = lp.loginAction(userName,password);
    }

    @When("^I add product (.+) to cart$")
    public void I_add_product_to_cart(String product) throws InterruptedException {
        pp.addProductToCart(product);
        cp = pp.goToCartPage();
    }

    @And("^I checkout product (.+) and submit order with month (.+) year (.+) cvv (.+) card name (.+) and country (.+)$")
    public void I_checkout_product_and_submit_order_with_month_year_cvv_card_name_and_country(String product,String month, String year, String cvv, String cardName, String country){
        Assert.assertTrue(cp.getProductCartList().stream().anyMatch(
                e->e.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(product)));
        //checkout page
        chp = cp.goToCheckout();
        chp.setExpiryDate(month,year);
        chp.setCvv(cvv);
        chp.setCardName(cardName);
        chp.selectCountry(country);
        //Confirmation page
        cop=chp.placeOrder();
    }

    @Then("I verify the confirmation message {string} is displayed on confirmation page")
    public void I_verify_the_confirmation_message_is_displayed_on_confirmation_page(String msg){
        Assert.assertTrue(cop.getConfirMessTitle().equalsIgnoreCase(msg));
        driver.close();
    }

    @Then("I verify the error message {string} is displayed")
    public void I_verify_the_error_message_is_displayed(String msg){
        Assert.assertEquals(lp.getErrorMessage(),msg);
        driver.close();
    }

}
