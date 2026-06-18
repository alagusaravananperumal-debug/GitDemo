
@tag
Feature: Purchase the order from ecommerce website
  I want to purchase the order from ecommerce
  
  Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And checkout the product <productName> and submit the order 
    Then "Thankyou for the order." message is displayed on Confirmation page

    Examples: 
      | username                 | 	password    | productName |
      | alagusaravanan@gmail.com |  Tharanya25! | ZARA COAT 3  |

