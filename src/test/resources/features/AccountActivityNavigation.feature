@smoke @regression
Feature: Navigating to specific accounts in Accounts Activity

  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page


  Scenario Outline: <link> account redirect
    When user clicks on "<link>" link on "Account Summary" page
    Then "Title" should contain "Zero - Account Activity" on "Account Activity" page
    And Account drop down should have "<dropdown>" selected on "Account Activity" page
    Examples:
      | link        | dropdown    |
      | Savings     | Savings     |
      | Brokerage   | Brokerage   |
      | Checking    | Checking    |
      | Credit Card | Credit Card |
      | Loan        | Loan        |
