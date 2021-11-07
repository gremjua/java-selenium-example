package com.selenium.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage {
    final private String url = "https://www.amazon.com/";
    WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;
    @FindBy(id = "nav-search-submit-button") 
    WebElement searchBoxSubmitButton;

    public String getUrl () {
        return url;
    }

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AmazonHomePage visit() {
        driver.get(this.url);
        return this;
    }

    public AmazonHomePage typeInSearchBox(String text) {
        searchBox.sendKeys(text);
        return this;
    }

    public AmazonSearchResultsPage submitSearch() {
        searchBoxSubmitButton.click();
        return new AmazonSearchResultsPage(driver);
    }
}
