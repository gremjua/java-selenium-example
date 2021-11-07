package com.selenium.example.steps;
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.selenium.example.pageObjects.*;

public class AmazonStepDefinitions {

    // private final String driverPath = "/Users/jgremes/repos/java-selenium-example/drivers/chromedriver";
    private WebDriver driver;
    private AmazonHomePage homePage;
    private AmazonSearchResultsPage searchResultsPage;
    private AmazonProductDetailPage productDetailPage;
    private AmazonProductAddedToCartPage productAddedToCartPage;
    private AmazonCartPage cartPage;
    private double productPrice;
    private int productQuantity;

    @Before()
    public void setup() {
        // System.setProperty("webdriver.chrome.driver", driverPath);
        Capabilities options;
        String browser;
        String remoteUrl;
        try{
            browser = System.getenv("BROWSER").toLowerCase();
        } catch(Exception e){
            browser = "chrome"; // default browser
        }

        if(browser.equals("edge")){
            options = new EdgeOptions();
        }
        else if(browser.equals("firefox")){
            options = new FirefoxOptions();
        }
        else {  // default to chrome
            options = new ChromeOptions();
        }

        try{
            remoteUrl = System.getenv("BASE_URL");
        } catch(Exception e){
            remoteUrl = "http://localhost:4444/wd/hub"; // default base url
        }

        try{
            driver = new RemoteWebDriver(new URL(remoteUrl), options);
        }catch(Exception e){
            throw new Error(e.getStackTrace().toString());
        }
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new AmazonHomePage(driver);
    }

    @Given("I visit Amazon main page")
    public void i_visit_amazon_main_page() {
        homePage.visit();
    }
    @Given("I search for {string}")
    public void i_search_for(String string) {
        searchResultsPage = homePage.typeInSearchBox(string).submitSearch();
    }
    @Given("I click on the first search result on the list")
    public void i_click_on_the_first_search_result_on_the_list() {
        productDetailPage = searchResultsPage.clickResultByIndex(0);
    }
    @Given("I get the price of the product")
    public void i_get_the_price_of_the_product() {
        productPrice = productDetailPage.getPrice();
    }
    @Given("I add the product to cart with quantity {int}")
    public void i_add_the_product_to_cart_with_quantity(Integer int1) {
        productAddedToCartPage = productDetailPage.selectQuantity(int1).addToCart();
        productQuantity = int1;
    }
    @Given("I open the cart")
    public void i_open_the_cart() {
        cartPage = productAddedToCartPage.goToCart();
    }
    @When("I reduce the product quantity by {int}")
    public void i_reduce_the_product_quantity_by(Integer int1) {
        for(int i = 0; i < int1.intValue(); i++){
            cartPage.removeOneFromCart();
            productQuantity--;
        }
    }
    @Then("I see the total price and quantity are correct")
    public void i_see_the_total_price_and_quantity_are_correct() {
        Assert.assertEquals(productQuantity, cartPage.getQuantity());
        cartPage.waitForSubtotalToBe(productPrice*productQuantity);
        Assert.assertEquals(productPrice*productQuantity, cartPage.getSubtotal(), 0.0);
    }
    @After()
    public void closeBrowser()
    {
        driver.quit();
    }
}
