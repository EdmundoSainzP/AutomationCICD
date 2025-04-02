package org.edspDeloitte.PageObjects;

import org.edspDeloitte.AbstractComponets.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends BasePage {
    WebDriver driver;



    @FindBy(xpath = "//tr[@class='ng-star-inserted']//td[2]")
    List<WebElement> orders;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrderDisplay(String n) {
        return orders.stream().anyMatch(e->e.getText().equalsIgnoreCase(n));
    }



}
