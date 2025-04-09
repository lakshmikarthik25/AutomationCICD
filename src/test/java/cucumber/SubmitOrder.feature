@tag
Feature: Purchase the order from Ecommers website
  I want to use this template for my feature file

Background:
Given I landed on  Ecommers page

  @Regresion
  Scenario Outline: Positive test of submitting the order
    Given Login by using username <name> and pasward <pasward>
    When add the product into the cart <productName>
    And checkout product <productName> and submit order
    Then "Thankyou for the order." message is displayed in the confirmationPage

    Examples: 
      | name                          | pasward    | productName     |
      | lakshmikarthik13256@gmail.com |Lakshmi@123 | ADIDAS ORIGINAL |
     
