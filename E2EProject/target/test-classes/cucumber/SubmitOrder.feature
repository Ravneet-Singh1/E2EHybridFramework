@tag
Feature: Purchase the order from Ecommerce website

  Background:
  Given I have landed on Ecommerce page


  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Enterting <username> and <password>
    When I add <product name> to cart
    And Checkout product with <country> and submit the order
    Then verify if "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username             | password  |product name    | country |
      | ravneet123@gmail.com | Ravneet@1 |ADIDAS ORIGINAL | India   |
      
