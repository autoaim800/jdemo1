Feature: toy robot ignore unknown command

  toy robot should be able to ignore unknown command
  known command after unknown command should be able to proceed

  Scenario: ignore invalid format of command1
    Given I have a table width 5
    Then I try to send "PLACE 0,0"

  Scenario: ignore invalid format of command2
    Given I have a table width 5
    Then I try to send "PLACE 0 0 NORTH"

  Scenario: ignore invalid command
    Given I have a table width 5
    Then try to dance

  Scenario: ignore invalid command after placed
    Given I have a table width 5
    And I place robot at "0,0,NORTH"
    Then try to dance

  Scenario: valid command after invalid command
    Given I have a table width 5
    And I place robot at "0,0,NORTH"
    Then try to dance
    And Robot should report as "0,0,NORTH"

  Scenario: valid command after two invalid commands
    Given I have a table width 5
    And I place robot at "0,0,NORTH"
    Then try to dance
    Then I try to send "JUMP 1,1,NORTH"
    And Robot should report as "0,0,NORTH"