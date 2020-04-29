@smoke @regression
Feature: Login functionality

  Background:
    Given user is on "Login" page


  Scenario: Only authorized users should be able to login to the application.

    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "Title" should contain "Account summary" on "Account summary" page


  Scenario: Users with wrong username and valid password should not be able to login.

    When user enters "username" as "Nijat" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "ErrorMessage" should contain "Login and/or password are wrong." on "Login" page

  Scenario: Users with valid username and wrong password should not be able to login.

    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "Nassif" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "ErrorMessage" should contain "Login and/or password are wrong." on "Login" page

  Scenario: Users with blank username and valid password should not be able to login.

    When user enters "username" as "" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "ErrorMessage" should contain "Login and/or password are wrong." on "Login" page

  Scenario: Users with valid username and blank password should not be able to login.

    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "ErrorMessage" should contain "Login and/or password are wrong." on "Login" page

  Scenario: Users with blank username and blank password should not be able to login.

    When user enters "username" as "" on "Login" page
    And user enters "password" as "" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "ErrorMessage" should contain "Login and/or password are wrong." on "Login" page