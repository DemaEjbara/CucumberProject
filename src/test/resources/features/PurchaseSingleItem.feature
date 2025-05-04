Feature: Complete purchase of single item

  Background:
    Given Open the Swag Labs Website
    When Enter  "standard_user" in the username field on Login Screen
    And Enter  "secret_sauce" in the password field on Login Screen
    And Press the login button
    Then Successful Login

  Scenario: Verify that items are sorted from A to Z after selecting the A to Z option from the sort dropdown
    Given Press the sorting drop down
    When User choose name "A to Z"
    Then Products are sorted from "A to Z" successfully

  Scenario: Verify that items are sorted from Z to A after selecting the Z to A option from the sort dropdown
    Given Press the sorting drop down
    When User choose name "Z to A"
    Then Products are sorted from "Z to A" successfully

  Scenario: Verify that items are sorted from low to high after selecting the low to high price  option from the sort dropdown
    Given Press the sorting drop down
    When User choose name "Low to High"
    Then Products are sorted from "Low to High" successfully

  Scenario: Verify that items are sorted from high to low after selecting the high to low price option from the sort dropdown
    Given Press the sorting drop down
    When User choose name "High to Low"
    Then Products are sorted from "High to Low" successfully

  Scenario: verify that the sauce labs backPack item is added to the cart
    Given The "Sauce Labs Bike Light" item is available
    When Press add to cart for "Sauce Labs Bike Light"
    Then cart number changes to "1"

  Scenario: verify that the cart icon is clickable
    Given Press on cart icon on the top right of the page
    Then Redirection to "https://www.saucedemo.com/v1/cart.html"

  Scenario: verify that the check out button which located in cart page is clickable
    Given Press on cart icon on the top right of the page
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press check out button
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"

  Scenario: verify that required fields must be filled to proceed in checkout
    Given Press on cart icon on the top right of the page
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press check out button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : "1234"
    And Click Continue
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-two.html"

  Scenario: verify error appears when postal code is missing in checkout
    Given Press on cart icon on the top right of the page
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press check out button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : ""
    And Click Continue
    Then Error button should  be displayed

  Scenario: verify that the continue button is clickable
    Given Press on cart icon on the top right of the page
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press check out button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : "1234"
    And Click Continue
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-two.html"

  Scenario: verify that the finish button is clickable
    Given Press on cart icon on the top right of the page
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press check out button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : "1234"
    And Click Continue
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-two.html"
    And Press finish
    And Redirection to "https://www.saucedemo.com/v1/checkout-complete.html"
