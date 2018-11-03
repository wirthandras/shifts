Feature: Employee actions
  Testing the employee actions

  Scenario: Login
    Given Nobody Logged in the application
    And The account input is empty
    And The password input is empty
    And John is an employee
    When John type his account name into account input field
    And John type his password name into password input field
    And John press the login button
    Then John is logged in

  Scenario: Setting Holiday
    Given John is an employee
    And John is logged in
    When John enter to holidays view
    Then the holidays view appeared
    And John holidays are shown in the view
    When John set a new holiday for own
    Then the holiday is appeared in his holidays view

  Scenario: Planned Sick-leave
    Given John is an employee
    And John is logged in
    When John enter to planned sicks view
    Then the planned sicks view appeared
    And John planned sicks are shown in the view
    When John set a new planned sick for own
    Then the planned sick is appeared in his planned sicks view
