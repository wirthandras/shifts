@tag
Feature: Shiftplanner
  Testing the Shiftplanner screen.

  @tag1
  Scenario: Shiftplanner
    Given The user is on the shift planner page
    Then The Shift form is visible
    Then The Submit button is visible
    When The Submit button is pressed
    Then The new shift element is added
#    And The Error message is hidden
    When The Submit button is pressed
    And The Error message is visible
    
