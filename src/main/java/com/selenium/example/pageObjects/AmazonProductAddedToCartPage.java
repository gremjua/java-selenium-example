package com.selenium.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonProductAddedToCartPage {
    WebDriver driver;

    @FindBy(id = "nav-cart")
    WebElement cartButton;

    public AmazonProductAddedToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AmazonCartPage goToCart() {
        cartButton.click();
        return new AmazonCartPage(driver);
    }

    
}
