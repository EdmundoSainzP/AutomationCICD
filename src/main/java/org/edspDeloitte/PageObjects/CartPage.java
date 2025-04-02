package org.edspDeloitte.PageObjects;

import org.edspDeloitte.AbstractComponets.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {
    WebDriver driver;

    By checkoutButtonBy = By.xpath("//a [text()='Place Order ']");
    @FindBy(css = ".cartWrap")
    List<WebElement> cartProducts;
    @FindBy( xpath="//button[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductCartList() {
        return cartProducts;
    }

    public CheckoutPage goToCheckout(){
        checkoutButton.click();
        waitForElementToAppear(checkoutButtonBy);
        CheckoutPage cp = new CheckoutPage(driver);
        return cp;
    }
}
