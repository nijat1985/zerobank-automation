Feature: Pay bills functionality

  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page
    When user navigates to "Pay Bills" page


  Scenario: Pay Bills page should have the title Zero – Pay Bills.
    Then "Title" should contain "Zero - Pay Bills" on "Pay Bills" page


  Scenario: When user completes a successful Pay operation,
  The payment was successfully submitted. should be displayed.
    When user enters "payee" as "Bank of America" on "Pay Bills" page
    And user enters "account" as "Checking" on "Pay Bills" page
    And user enters "amount" as "5" on "Pay Bills" page
    And user enters "date" as "05/01/2020" on "Pay Bills" page
    And user clicks on "pay" button on "Pay Bills" page
    Then "Message" should contain "The payment was successfully submitted." on "Pay Bills" page



  Scenario Outline: When user tries to make a payment without entering the amount or date,
  Please fill out this field. message should be displayed.
    When user enters "payee" as "Bank of America" on "Pay Bills" page
    And user enters "account" as "Checking" on "Pay Bills" page
    And user enters "amount" as "<amount>" on "Pay Bills" page
    And user enters "date" as "<date>" on "Pay Bills" page
    And user clicks on "pay" button on "Pay Bills" page
    Then "Empty field message" should contain "Please fill out this field." on "Pay Bills" page
    Examples:
      | amount | date       |
      |        | 05/01/2020 |
      | 10     |            |



  Scenario Outline: Amount field should not accept alphabetical or special characters.
    When user enters "payee" as "Bank of America" on "Pay Bills" page
    And user enters "account" as "Checking" on "Pay Bills" page
    And user enters "amount" as "<amount>" on "Pay Bills" page
    And user enters "date" as "05/01/2020" on "Pay Bills" page
    And user clicks on "pay" button on "Pay Bills" page
    Then "After payment message" shouldn't contain "The payment was successfully submitted." on "Pay Bills" page
    Examples:
      | amount |
      | dd     |
      | /      |

  @wip
   Scenario: Date field should not accept alphabetical characters.
     When user enters "payee" as "Bank of America" on "Pay Bills" page
     And user enters "account" as "Checking" on "Pay Bills" page
     And user enters "amount" as "15" on "Pay Bills" page
     And user enters "date" as "dd" on "Pay Bills" page
     And user clicks on "pay" button on "Pay Bills" page
     Then "Empty field message" should contain "Please fill out this field." on "Pay Bills" page


