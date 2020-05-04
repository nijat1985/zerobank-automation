@smoke @regression
Feature: Statements and Documents details

  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page
    When user navigates to "Online Statements" "page"


  Scenario Outline: Recent statements per <year>
    And the user selects the Recent Statements Year <year>
    Then <count> statements should be displayed for that year
    Examples:
      | year | count |
      | 2009 | 2     |
      | 2010 | 2     |
      | 2011 | 2     |
      | 2012 | 1     |


  Scenario Outline: Download statements <year>
    And the user selects the Recent Statements Year <year>
    And the user clicks on statement "<statement>"
    Then the downloaded file name should contain "<name>"
    And the file type should be ".pdf"
    Examples:
      | year | statement               | name     |
      | 2009 | Statement 31/11/09(57K) | 31-11-09 |
      | 2010 | Statement 01/12/10(57K) | 01-12-10 |
      | 2011 | Statement 05/12/11(57K) | 05-12-11 |
      | 2012 | Statement 01/10/12(57K) | 01-10-12 |