#created a feature file to tackle all the scenarios mentioned below
@sauceDemo
Feature: Managing the cart

#to login with valid/invalid inputs
  @login
  Scenario: To login with given details
    Given the user is on login page
    When the details are entered

#adding items to the cart
  @inventory
  Scenario: sorting and adding the items to the cart
    When the option in the drop down is selected
    Then the items are sorted
    And three items are added to the cart
  
#removing an item from the cart
  Scenario: removing items from the cart 
  When one item is removed from the cart
  Then the cart is updated
  And the browser quits
  
  
