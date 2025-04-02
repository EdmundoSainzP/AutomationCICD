package org.edspDeloitte.PageObjects;

import org.edspDeloitte.AbstractComponets.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    WebDriver driver;

    By confirMessTitle =  By.cssSelector(".hero-primary");
    @FindBy(xpath = "//div[text()='CVV Code ']//following-sibling::input")
    WebElement cvvCode;
    @FindBy (xpath = "//div[text()='Name on Card ']//following-sibling::input")
    WebElement cardName;
    @FindBy(xpath = "//input [@placeholder = 'Select Country']")
    WebElement searchCountry;
    @FindBy(xpath = "//a [text()='Place Order ']")
    WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setExpiryDate(String m , String y) {
        driver.findElement(By.xpath("//div[contains(text(),'Expiry Date')]//following-sibling::select[1]//option[text()='"+m+"']")).click();
        driver.findElement(By.xpath("//option[text()='"+y+"']")).click();
    }
    public void setCvv(String c) {
        cvvCode.sendKeys(c);
    }
    public void selectCountry(String c) {
        searchCountry.sendKeys(c.substring(0,2));
        waitForElementToAppear(By.xpath("//span[contains(text(),'"+c.substring(0, 1).toUpperCase() + c.substring(1)+"')]//parent::button"));
        driver.findElement(By.xpath("//span[contains(text(),'"+c.substring(0, 1).toUpperCase() + c.substring(1)+"')]//parent::button")).click();
    }

    public ConfirmationPage placeOrder(){
        placeOrderButton.click();
        waitForElementToAppear(confirMessTitle);
        ConfirmationPage cp = new ConfirmationPage(driver);
        return cp;
    }

    public void setCardName(String cn) {
        cardName.sendKeys(cn);
    }
}
