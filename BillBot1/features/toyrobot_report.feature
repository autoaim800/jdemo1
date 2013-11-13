Feature: toy robot report

  command report should be ignored prior to place
  command report should not be nil after place

  Scenario: ignore report prior place
    Given a table width 5
    Then try to report

  Scenario: ignore report prior place with move
    Given a table width 5
    And try to move
    Then try to report

  Scenario: ignore report prior place with turn right
    Given a table width 5
    And try to turn right
    Then try to report

  Scenario: ignore report prior place with turn left
    Given a table width 5
    And try to turn left
    Then try to report

  Scenario: ignore report prior place
    Given a table width 5
    And place at "0,0,NORTH"
    Then manage to report