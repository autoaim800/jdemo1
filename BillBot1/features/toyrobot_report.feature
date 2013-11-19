Feature: toy robot report

  command report should be ignored prior to place
  command report should not be nil after place

  Scenario: ignore report prior place
    Given I have a table width 5
    Then I try to report

  Scenario: ignore report prior place with move
    Given I have a table width 5
    And try to move
    Then I try to report

  Scenario: ignore report prior place with I command turn right
    Given I have a table width 5
    And I try to turn right
    Then I try to report

  Scenario: ignore report prior place with turn left
    Given I have a table width 5
    And I try to turn left
    Then I try to report

  Scenario: ignore report prior place
    Given I have a table width 5
    And I place robot at "0,0,NORTH"
    Then manage to report