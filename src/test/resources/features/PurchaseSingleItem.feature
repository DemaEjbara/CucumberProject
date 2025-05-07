Feature: Complete purchase of single item

  Background:
    When Enter  "standard_user" in the username field on Login Screen
    And Enter  "secret_sauce" in the password field on Login Screen
    And Press on "login" button
    Then Successful Login

  Scenario Outline: Verify that items are sorted correctly from the sort dropdown
    Given Press on "sortingDropDown" button
    When User choose name "<sort type>"
    Then Products are sorted from "<expected result>" successfully

    Examples:
      | sort type     | expected result |
      | A to Z        | A to Z          |
      | Z to A        | Z to A          |
      | Low to High   | Low to High     |
      | High to Low   | High to Low     |

  Scenario: verify that the sauce labs backPack item is added to the cart
    Given The "Sauce Labs Bike Light" item is available
    When Press add to cart for "Sauce Labs Bike Light"
    Then cart number changes to "1"

  Scenario: verify that the cart icon is clickable
    Given Press on "cartIcon" button
    Then Redirection to "https://www.saucedemo.com/v1/cart.html" and contains title "pageTitleCart"

  Scenario: verify that the check out button which located in cart page is clickable
    Given Press on "cartIcon" button
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press on "checkOut" button
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"

  Scenario: verify that required fields must be filled to proceed in checkout
    Given Press on "cartIcon" button
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press on "checkOut" button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert "Dema" into "firstName"
    And Insert "Ejbara" into "lastName"
    And Insert "1234" into "postalCode"
    And Press on "continue" button
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-two.html"

  Scenario: verify error appears when postal code is missing in checkout
    Given Press on "cartIcon" button
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press on "checkOut" button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert "Dema" into "firstName"
    And Insert "Ejbara" into "lastName"
    And Insert "" into "postalCode"
    And Press on "continue" button
    Then Error button should  be displayed

  Scenario: verify that the continue button is clickable
    Given Press on "cartIcon" button
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press on "checkOut" button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert "Dema" into "firstName"
    And Insert "Ejbara" into "lastName"
    And Insert "1234" into "postalCode"
    And Press on "continue" button
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-two.html"

  Scenario: verify that the finish button is clickable
    Given Press on "cartIcon" button
    When Redirection to "https://www.saucedemo.com/v1/cart.html"
    And Press on "checkOut" button
    And Redirection to "https://www.saucedemo.com/v1/checkout-step-one.html"
    And Insert "Dema" into "firstName"
    And Insert "Ejbara" into "lastName"
    And Insert "1234" into "postalCode"
    And Press on "continue" button
    Then Redirection to "https://www.saucedemo.com/v1/checkout-step-two.html"
    And Press on "finish" button
    And Redirection to "https://www.saucedemo.com/v1/checkout-complete.html"








