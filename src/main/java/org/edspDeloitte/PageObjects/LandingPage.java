package org.edspDeloitte.PageObjects;

import org.edspDeloitte.AbstractComponets.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    WebDriver driver;
    //without page factoring
    //WebElement userEmail = driver.findElement(By.id("userEmail"));
    //WebElement userPassword = driver.findElement(By.id("userPassword"));
    //WebElement loginSubmit = driver.findElement(By.id("login"));
    //with page factoring
    By registerBy = By.xpath("//a[text()='Register']");
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement loginSubmit;
    @FindBy(css = "div .toast-message")
    WebElement errorMessage;


    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
        waitForElementToAppear(registerBy);
        zoomOut();
    }

    public ProductPage loginAction(String u, String p){
        userEmail.sendKeys(u);
        userPassword.sendKeys(p);
        loginSubmit.click();

        ProductPage pp = new ProductPage(driver);
        return pp;
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

}
