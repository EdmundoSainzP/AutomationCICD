package org.edspDeloitte.AbstractComponets;

import org.edspDeloitte.PageObjects.CartPage;
import org.edspDeloitte.PageObjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    By checkoutButtonBy = By.xpath("//button[contains(text(),'Checkout')]");
    By ordersBy = By.xpath("//tr[@class='ng-star-inserted']");
    @FindBy( xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartButton;
    @FindBy( xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement ordersButton;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By byLocator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public void waitForWebElementToAppear(WebElement w){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(w));
    }

    public void waitForElementToDisappear(By byLocator) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
    }

    public void zoomOut(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String zoomChrome = "document.body.style.zoom = '50.0%'"; //edge, safari, chrome
        //zoomFirefox = "document.body.style.MozTransform = 'scale(0.5)';"; //Firefox
        js.executeScript(zoomChrome);
    }

    public void zoomIn(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String zoomChrome = "document.body.style.zoom = '100.0%'"; //edge, safari, chrome
        //zoomFirefox = "document.body.style.MozTransform = 'scale(0.5)';"; //Firefox
        js.executeScript(zoomChrome);
    }

    public void goToElement(WebElement e){
       new Actions(driver).scrollToElement(e).perform();
    }

    public CartPage goToCartPage(){
        cartButton.click();
        waitForElementToAppear(checkoutButtonBy);
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage() {
        ordersButton.click();
        waitForElementToAppear(ordersBy);
        return new OrdersPage(driver);
    }
}

