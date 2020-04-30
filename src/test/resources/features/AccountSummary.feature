@smoke @regression
Feature: Account summary functionality

  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page


  Scenario: Account summary page should have the title Zero – Account summary.
    Then "Title" should contain "Zero - Account Summary" on "Account summary" page


  Scenario: Account summary page should have to following account types: Cash Accounts, Investment Accounts, Credit Accounts, Loan Accounts.
    Then "Account types" should contain the following information on "Account summary" page
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |

  Scenario: Credit Accounts table must have columns Account, Credit Card and Balance.
    Then "Credit Accounts" table must have following columns on "Account summary" page
      | Account     |
      | Credit Card |
      | Balance     |