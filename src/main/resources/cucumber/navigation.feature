@tag
Feature: Menu
  I want to use this template to check the Menu structure is accessible

  @tag1
  Scenario: Home page
    Given Open the application
    Then The Home button is visible
    And The Generate button is visible
    And The Employees button is hidden
    And The Cars button is hidden
    And The Shifts button is hidden
    And The New Car button is hidden
    And The New Employee button is hidden
    When The Details button is pressed
    Then The Employees button is visible
    And The Cars button is visible
    And The Shifts button is visible 
    
    