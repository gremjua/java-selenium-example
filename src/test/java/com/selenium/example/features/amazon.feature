@smokeTest
Feature: Shopping Cart
Search for products, add and remove from cart.

Background: Visit Amazon and add a product to cart
    Given I visit Amazon main page
    And I search for "hats for men"
    And I click on the first search result on the list
    And I get the price of the product
    And I add the product to cart with quantity 2

Scenario: The cart shows correct values
    When I open the cart
    Then I see the total price and quantity are correct

Scenario: Remove product
    Given I open the cart
    When I reduce the product quantity by 1
    Then I see the total price and quantity are correct

