@smoke @regression @wip
Feature: Login functionality

  Background:
    Given user is on "Login" page


  Scenario: Only authorized users should be able to login to the application.
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "Title" should contain "Zero - Account Summary" on "Account summary" page


  Scenario Outline: Users with invalid or blank (username or password) should not be able to login.
    When user enters "username" as "<username>" on "Login" page
    And user enters "password" as "<password>" on "Login" page
    And user clicks on "Login" button on "Login" page
    Then "ErrorMessage" should contain "Login and/or password are wrong." on "Login" page

    Examples:
      | username      | password      |
      | Nijat         | user_password |
      | user_username | Nassif        |
      |               | user_password |
      | user_username |               |
      |               |               |