package com.selenium.example.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Amazon Cart page object. Currently set up to support one product in cart.
 */
public class AmazonCartPage {
    WebDriver driver;

    @FindBy(css = "[data-feature-id='sc-update-quantity-select']")
    WebElement quantityDropdownMenu;
    @FindBy(xpath = "//*[@id='a-popover-2']//li")
    List<WebElement> quantityOptions;
    private final String subtotalLocator = "//*[@id='sc-subtotal-amount-activecart']/span";
    @FindBy(xpath = subtotalLocator)
    WebElement subtotal;


    public AmazonCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AmazonCartPage addOneToCart() {
        int currentQuantity = getQuantity();
        quantityDropdownMenu.click();
        quantityOptions.get(currentQuantity+1).click();
        return this;
    }

    public AmazonCartPage removeOneFromCart() {
        int currentQuantity = getQuantity();
        quantityDropdownMenu.click();
        quantityOptions.get(currentQuantity-1).click();
        return this;
    }

    public int getQuantity() {
        return Integer.parseInt(quantityDropdownMenu.findElement(By.className("a-dropdown-prompt")).getText());
    }

    public double getSubtotal() {
        return Double.parseDouble(subtotal.getText().replace("$", ""));
    }

    public AmazonCartPage waitForSubtotalToBe(double num) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.textToBe(By.xpath(subtotalLocator), String.format("$%.2f", num)));
        return this;
    }

}
