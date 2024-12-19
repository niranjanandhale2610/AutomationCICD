
@tag
Feature: Purchase the order from Ecommerce site
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page

  @tag1
  Scenario Outline: Positive Test of submitting order
  
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on confirmationPage

    Examples: 
      | username                       | password        | productName  |
      | niranjan.andhale@gmail.com | Niranjan@1991   |  ZARA COAT 3 |
      
