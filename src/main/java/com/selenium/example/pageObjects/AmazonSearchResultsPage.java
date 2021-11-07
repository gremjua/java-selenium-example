package com.selenium.example.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonSearchResultsPage {
    WebDriver driver;

    @FindBy(css = "[cel_widget_id*='MAIN-SEARCH_RESULTS-']")
    List<WebElement> searchResults;

    public AmazonSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AmazonProductDetailPage clickResultByIndex(int index) {
        searchResults.get(index).click();
        return new AmazonProductDetailPage(driver);
    }
    
}
