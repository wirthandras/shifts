Feature: Admin actions
  Testing the admin actions

  Scenario: Login
    Given Nobody Logged in the application
    And The account input is empty
    And The password input is empty
    And Joe is an admin
    When Joe type his account name into account input field
    And Joe type his password name into password input field
    And Joe press the login button
    Then Joe is logged in
    And The admin view is visible

  Scenario: Add New Car
    Given Joe is an admin
    And Joe is watching the New Car view
    When Joe type a plate number to Plate Number input field
    And select the Car Type
    And Press the add button
    Then The Car is stored in system

  Scenario: Add New Employee
    Given Joe is an admin
    And watching the New Employee view
    When Joe type the new employee name to name input field
    And select the job of employee
    And press the add button
    Then The employee is stored in system

  Scenario: Add a temporary shift interval
    Given Joe is an admin
    And watching the shiftplanner view
    When Joe set the starting hour of the shift
    And set the end hour of the the shift
    And set a concrete car
    And press the add button
    Then The interval is added to temporary list

  Scenario: remove a temporary shift interval
    Given Joe is an admin
    And watching the shiftplanner view
    When Joe remove a shift interval
    Then The shift interval is removed from temporary list

  Scenario: Generate shifts
    Given Joe is an admin
    When Joe press the generate button
    Then The application generates shifts based on shift intervals for each day in month

  Scenario: Clear monthly plan
    Given Joe is an admin
    And Joe is planning the next month
    And The a plan is already generated
    When Joe press the clear button
    Then The shifts are removed

  Scenario: Clear shift
    Given Joe is an admin
    And Joe is planning the next month
    And The shifts generated based on daily intervals
    When Joe remove a shift
    Then The shifts are removed from shifts view
    But The shift interval is remain in daily planning view
