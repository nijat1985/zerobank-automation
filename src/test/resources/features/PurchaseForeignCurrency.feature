@smoke @regression
Feature: Purchase Foreign Currency

  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page
    When user navigates to "Pay Bills" "page"
    And user accesses to "Purchase Foreign Currency" "tab"


  Scenario: Available currencies
    Then "Currency drop down" should contain the following information on "Pay Bills" page
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |


  Scenario Outline: Error message for not selecting currency
    When user tries to calculate cost without selecting a "<options>"
    Then error message "Please, ensure that you have filled all the required fields with valid values." should be displayed

    Examples:
      | options  |
      | currency |
      | value    |

