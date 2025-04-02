@tag
  Feature: Purchase the order from Ecommerce website

    Background:
      Given I landed on Ecommerce page

    @Regression
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add product <prodName> to cart
      And I checkout product <prodName> and submit order with month <month> year <year> cvv <cvv> card name <cardName> and country <country>
      Then I verify the confirmation message "Thankyou for the order." is displayed on confirmation page

      Examples:
        | name  | password |  prodName  | month | year  | cvv | cardName  | country |
        | esainzpalacios@gmail.com |  Yo@nofui31 |  ZARA COAT 3 | 12  | 31  | 324 | Edmundo Sanchez   | mexico  |
#        | esainzpalacios@gmail.com |  Yo@nofui31 |  ADIDAS ORIGINAL | 11  | 30  | 324 | David Sanchez   | argentina  |
#        | esainzpalacios@gmail.com |  Yo@nofui31 |  IPHONE 13 PRO | 10  | 29  | 324 | Edmundo Palacios   | spain  |