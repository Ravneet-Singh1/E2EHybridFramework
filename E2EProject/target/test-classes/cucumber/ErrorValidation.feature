
@tag
Feature: Error Validation

  @errorValidation
  Scenario Outline: Negative Test for Error Validation
    Given I have landed on Ecommerce page
    When Enterting <username> and <password>
    Then Validate if we are getting the <error message> 

    Examples: 
      | username             | password     | error message                |
      | ravneet@gmail.com    | password1212 | Incorrect email or password. |
      | ravneet123@gmail.com | Ravneet      | Incorrect email or password. |
