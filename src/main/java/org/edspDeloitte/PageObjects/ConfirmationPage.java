package org.edspDeloitte.PageObjects;

import org.edspDeloitte.AbstractComponets.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class ConfirmationPage extends BasePage {
    WebDriver driver;
    @FindBy(css = ".hero-primary")
    WebElement confirMessTitle;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirMessTitle() {
        return confirMessTitle.getText();
    }
}
