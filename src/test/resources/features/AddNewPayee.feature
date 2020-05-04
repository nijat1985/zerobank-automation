@smoke @regression
Feature: Add new payee under pay bills

  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page
    And user navigates to "Pay Bills" "page"
    And user accesses to "Add New Payee" "tab"


  Scenario: Add a new payee
    And creates new payee using following information
      | Payee Name    | The Law Offices of Hyde, Price & Scharks |
      | Payee Address | 100 Same st, Anytown, USA, 10001         |
      | Account       | Checking                                 |
      | Payee details | XYZ account                              |
    Then "Add new payee message" should contain "The new payee The Law Offices of Hyde, Price & Scharks was successfully created." on "Pay Bills" page