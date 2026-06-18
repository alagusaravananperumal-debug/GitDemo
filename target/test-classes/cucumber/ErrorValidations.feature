
@tag
Feature: Error validations
  I want to purchase the order from ecommerce
  
  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
    Given I landed on Ecommerce Page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed on login page

    Examples: 
      | username                 | 	password    |
      | alagusaravanan@gmail.com |  Tharanya2   | 

