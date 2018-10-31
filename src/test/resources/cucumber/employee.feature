Feature: Employee actions
  Testing the employee actions

  Scenario: Login
    Given Nobody Logged in the application
    And The account input is empty
    And The password input is empty
    When The employee type his account name into account input field
    And The employee type his password name into password input field
    And The employee press the login button
    Then The employee is logged in
    And The employee view is visible
    And The shift wishes page is visible
    And The holidays page is visible
    And The sicks page is visible
    And The profile page is visible
    

