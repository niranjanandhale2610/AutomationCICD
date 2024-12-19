
@tag
Feature: Error Validation Test
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Error Validation
    Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username                   | password    |
      | niranjan.andhale@gmail.com | Niranjan@   |
