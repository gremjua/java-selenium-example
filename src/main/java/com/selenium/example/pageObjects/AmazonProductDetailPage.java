package com.selenium.example.pageObjects;

import java.io.Console;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductDetailPage {
    WebDriver driver;

    @FindBy(className = "a-dropdown-label")
    WebElement quantity;
    @FindBy(id = "quantity")
    Select quantityDropdownMenu;

    @FindBy(css = "[id*='quantity_']")
    List<WebElement> quantityListItems;

    @FindBy(id = "add-to-cart-button") 
    WebElement addToCartButton;
    @FindBy(xpath = "//*[@id='corePrice_desktop']/div/table/tbody/tr/td[2]/span[1]")
    WebElement price;

    public AmazonProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AmazonProductDetailPage selectQuantity(int number) {
        quantity.click();
        quantityListItems.get(number).click();
        return this;
    }

    public AmazonProductAddedToCartPage addToCart() {
        addToCartButton.click();
        return new AmazonProductAddedToCartPage(driver);
    }

    public double getPrice() {
        return Double.parseDouble(price.getText().replace("$", ""));
    }

    
}
