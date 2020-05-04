@smoke @regression
Feature: Account activity functionality

  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page
    When user navigates to "Account Activity" "page"

  Scenario: Account Activity page should have the title Zero – Account activity.
    Then "Title" should contain "Zero - Account Activity" on "Account Activity" page


  Scenario: The Account drop down default option should be Savings.
    Then "Account drop down default option" should contain "Savings" on "Account Activity" page


  Scenario: Account drop down should have the following options: Savings, Checking, Loan, Credit Card, Brokerage.
    Then "Account drop down" should contain the following information on "Account Activity" page
      | Savings     |
      | Checking    |
      | Loan        |
      | Credit Card |
      | Brokerage   |


  Scenario: Transactions table should have column names Date, Description, Deposit, Withdrawal.
    Then "Transactions" table must have following columns on "Account Activity" page
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
