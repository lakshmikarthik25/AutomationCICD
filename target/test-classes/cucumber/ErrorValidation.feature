
@tag
Feature: Testing error Validation
  I want to use this template for my feature file


Background:


  @ErrorBaap
Scenario Outline: Positive test of submitting the order
    Given I landed on  Ecommers page
    When Login by using username <name> and pasward <pasward>
    Then "Incorrect email or password." message displayed 
    

  Examples: 
      | name                          | pasward    | productName     |
      | lakshmikarthik156@gmail.com   |Lakshmi@123 | ADIDAS ORIGINAL |