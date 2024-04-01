Feature: I can create a hotel reservation as a user

  Scenario: I can create and update a hotel reservation as a user
    Given Creating a new reservation
    And I provide the necessary information for booking
    When Creating my hotel reservation
    Then My reservation was successfully created
    And I cancel the reservation I created