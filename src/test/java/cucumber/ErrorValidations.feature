@tag
Feature: Error validations

  Background:
    Given I landed on Ecommerce page

  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    Then I verify the error message "Incorrect email or password." is displayed

    Examples:
      | name  | password |
      | esainzpalacios1@gmail.com |  Yo@nofui31 |
#      | esainzpalacios@gmail.com |  Yo@nofui311 |
#      | null |  null |