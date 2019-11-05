Feature: User management

  Scenario: Retrieve user
    When I search user 'zina'
    Then the user is found
    And his last fullName is 'ZINA LACINA'
