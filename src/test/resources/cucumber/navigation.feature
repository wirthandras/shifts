@tag
Feature: Menu Check
  Testing the navigation bar.

  @tag1
  Scenario: Home page
    Given Open the application
    Then The Home button is visible
    And The Generate button is visible
    And The Employees button is hidden
    And The Shifts button is hidden
    And The New Employee button is hidden
    And The Shiftplanner button is hidden
    When The Details button is hovered
    Then The Employees button is visible
    And The Shifts button is visible
    And The Monthly Hours button is visible
    When The New button is hovered
    And The New Employee button is visible
    When The Planning button is hovered
    Then The Shiftplanner button is visible

