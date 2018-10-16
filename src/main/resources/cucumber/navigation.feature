@tag
Feature: Menu
  I want to use this template to check the Menu structure is accessible

  @tag1
  Scenario: Home page
    Given Open the application
    Then The Home button is available
    And The Generate button is available
    And The Employees button is hidden
    And The Cars button is hidden
    And The Shifts button is hidden
    
    