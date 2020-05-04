@smoke @regression
Feature: Find Transactions in Account Activity


  Background:
    Given user is on "Login" page
    When user enters "username" as "user_username" on "Login" page
    And user enters "password" as "user_password" on "Login" page
    And user clicks on "login" button on "Login" page
    And user navigates to "Account Activity" "page"
    And user accesses to "Find Transactions" "tab"


  Scenario: Search date range
    When user enters "start date" as "2012-09-01" on "Account Activity" page
    And user enters "end date" as "2012-09-06" on "Account Activity" page
    And user clicks on "find" button on "Account Activity" page
    Then "Transactions table" should only show transactions dates between "2012-09-01" to "2012-09-06" on "Account Activity" page
    And the results should be sorted by most recent date
    When user enters "start date" as "2012-09-02" on "Account Activity" page
    And user enters "end date" as "2012-09-06" on "Account Activity" page
    And user clicks on "find" button on "Account Activity" page
    Then "Transactions table" should only show transactions dates between "2012-09-02" to "2012-09-06" on "Account Activity" page
    And the results table should only not contain transactions date "2012-09-01"


  Scenario: Search description
    When user enters "description" as "ONLINE" on "Account Activity" page
    And user clicks on "find" button on "Account Activity" page
    Then results table should only show descriptions containing "ONLINE"
    When user enters "description" as "OFFICE" on "Account Activity" page
    And user clicks on "find" button on "Account Activity" page
    Then results table should only show descriptions containing "OFFICE"
    But results table should not show descriptions containing "ONLINE"


  Scenario: Search description case insensitive
    When user enters "description" as "ONLINE" on "Account Activity" page
    And user clicks on "find" button on "Account Activity" page
    Then results table should only show descriptions containing "ONLINE"
    When user enters "description" as "online" on "Account Activity" page
    And user clicks on "find" button on "Account Activity" page
    Then results table should only show descriptions containing "ONLINE"


  Scenario: Type
    And user clicks on "find" button on "Account Activity" page
    Then results table should show at least one result under "Deposit"
    Then results table should show at least one result under "Withdrawal"
    When user selects type "Deposit"
    And user clicks on "find" button on "Account Activity" page
    Then results table should show at least one result under "Deposit"
    But results table should show no result under "Withdrawal"
    When user selects type "Withdrawal"
    And user clicks on "find" button on "Account Activity" page
    Then results table should show at least one result under "Withdrawal"
    But results table should show no result under "Deposit"