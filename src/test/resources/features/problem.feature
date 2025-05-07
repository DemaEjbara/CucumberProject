Feature: Verify that the existing problems in swag labs website
  Background:
    When Enter  "problem_user" in the username field on Login Screen
    And Enter  "secret_sauce" in the password field on Login Screen
    And Press on "login" button
    Then Successful Login

  Scenario: adding the Sauce Labs Fleece Jacket item to the cart
    Given The "Sauce Labs Fleece Jacket" item is available
    And Press add to cart for "Sauce Labs Fleece Jacket"
    When Add to cart changed to remove
    Then cart number changes to "1"

    Scenario: adding the Sauce labs Bolt T-Shirt from cart
      Given The "Sauce Labs Bolt T-Shirt" item is available
      When Press add to cart for "Sauce Labs Bolt T-Shirt"
      Then cart number changes to "1"




