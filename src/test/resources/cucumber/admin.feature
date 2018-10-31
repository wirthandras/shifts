Feature: Admin actions
  Testing the admin actions


  Scenario: Login
    Given Nobody Logged in the application
    And The account input is empty
    And The password input is empty
    When The admin type his account name into account input field
    And The admin type his password name into password input field
    And The admin press the login button
    Then The admin is logged in
    And The admin view is visible
  

  Scenario: Monthly Shift planning
    Given The shiftplanner view on the screen
    When The admin adds intervals
    And The admin press the generate button
    Then The intervals applied each day in month
