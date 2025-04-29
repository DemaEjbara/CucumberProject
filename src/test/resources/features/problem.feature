Feature: Verify that the existing problems in swag labs website
  Background:
    Given Open the Swag Labs Website
    When Enter  "problem_user" in the username field on Login Screen
    And Enter  "secret_sauce" in the password field on Login Screen
    And Press the login button
    And Wait for 3
    Then Successful Login
    And Wait for 3

  Scenario: adding the Sauce Labs Fleece Jacket item to the cart
    Given I click on add to cart button for Sauce Labs Fleece Jacket
    And Wait for 3
    When Add to cart changed to remove
    And Wait for 3
    Then cart number changes to "1"

    Scenario: adding the Sauce labs Bolt T-Shirt from cart
      Given The sauce labs Bolt T-Shirt item is available
      When Press add to cart for Sauce Labs Bolt T-Shirt
      And Wait for 3
      Then cart number changes to "1"




