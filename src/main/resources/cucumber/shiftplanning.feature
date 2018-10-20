@tag
Feature: Shiftplanner
  Testing the Shiftplanner screen.

  @tag1
  Scenario: Shiftplanner
    Given Open the application
    Then The Planning button is visible
    And The Shiftplanner button is hidden
    When The Planning button is hovered
    Then The Shiftplanner button is visible
    When The Shiftplanner button is pressed
    Then The Shift form is visible
    Then The Submit button is visible
    When The Submit button is pressed
    Then The new shift element is added
#    And The Error message is hidden
    When The Submit button is pressed
    And The Error message is visible
    
