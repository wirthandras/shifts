@tag
Feature: Create New Car
  I want to use this template for my feature file

  @tag1
  Scenario: Create New Car
    Given The user is on the create new car page
    Then The NewCarForm form is visible
    And The Plate Number input field is visible
    And The Car type dropdown list is visible
    And The Submit button is visible
    When Set "TTT-001" to Plate Number input field
    And The Submit button is pressed
    And Set "TTT-001" to Plate Number input field
    And The Submit button is pressed
    Then The Error message is visible
    And Set "TTT-002" to Plate Number input field
    And The Submit button is pressed
    When Set "Invalid Plate Number" to Plate Number input field 
    And The Submit button is pressed
    Then The Error message is visible
    