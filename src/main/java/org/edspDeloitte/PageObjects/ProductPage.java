package org.edspDeloitte.PageObjects;

import org.edspDeloitte.AbstractComponets.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends BasePage {
    WebDriver driver;
    //without page factoring
    //driver.findElements(By.cssSelector(".mb-3"))
    //driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"))
    //
    //with page factoring
    By productsBy =By.cssSelector(".mb-3");
    //By addToCartBy = By.xpath("//button[contains(text(),' Add To Cart')]");
    By addToCartBy = By.cssSelector(".card-body button:last-of-type");
    By loadingSpinnerBy = By.cssSelector(".ngx-spinner-overlay");

    @FindBy(css = ".mb-3")
    List<WebElement> products;


    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String prodName){
        return getProductList().stream().filter(
                e->e.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);


    }
    public void addProductToCart(String prodName) throws InterruptedException {
        getProductByName(prodName).findElement(addToCartBy).click();
        waitForElementToAppear(loadingSpinnerBy);
        waitForElementToDisappear(loadingSpinnerBy);
    }
}
