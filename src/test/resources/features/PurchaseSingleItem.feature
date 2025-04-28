Feature: Complete purchase of single item

  Background:
    Given Open the Swag Labs Website
    When Enter  "standard_user" in the username field on Login Screen
    And Enter  "secret_sauce" in the password field on Login Screen
    And Press the login button
    Then Successful Login
    And Wait for 3

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
    Given The sauce labs backPack item is available
    When Press add to cart for Sauce Labs Backpack
    And Wait for 3
    Then cart number change to "1" item

  Scenario: verify that the cart icon is clickable
    Given Press on cart icon on the top right of the page
    Then Redirection to the cart page

  Scenario: verify that the check out button which located in cart page is clickable
    Given Press on cart icon on the top right of the page
    When Redirection to the cart page
    And Press check out button
    And Wait for 3
    Then Redirection to the Checkout : Your Information

  Scenario: verify that required fields must be filled to proceed in checkout
    Given Press on cart icon on the top right of the page
    When Redirection to the cart page
    And Press check out button
    And Wait for 3
    And Redirection to the Checkout : Your Information
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : "1234"
    And Click Continue
    And Wait for 3
    Then Redirection to Checkout : Overview

  Scenario: verify error appears when postal code is missing in checkout
    Given Press on cart icon on the top right of the page
    When Redirection to the cart page
    And Press check out button
    And Redirection to the Checkout : Your Information
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : ""
    And Click Continue
    Then Error button should  be displayed

  Scenario: verify that the continue button is clickable
    Given Press on cart icon on the top right of the page
    When Redirection to the cart page
    And Press check out button
    And Redirection to the Checkout : Your Information
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : "1234"
    And Click Continue
    And Wait for 3
    Then Redirection to Checkout : Overview

  Scenario: verify that the finish button is clickable
    Given Press on cart icon on the top right of the page
    When Redirection to the cart page
    And Press check out button
    And Redirection to the Checkout : Your Information
    And Insert first name "Dema" , Last name : "Ejbara" and postal code : "1234"
    And Click Continue
    And Wait for 3
    Then Redirection to Checkout : Overview
    And Press finish
    And Check Checkout : Complete!
