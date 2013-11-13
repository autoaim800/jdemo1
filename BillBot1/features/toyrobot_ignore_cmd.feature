Feature: toy robot ignore unknown command

  toy robot should be able to ignore unknown command
  known command after unknown command should be able to proceed

  Scenario: ignore invalid format of command1
    Given a table width 5
    Then try to send "PLACE 0,0"

  Scenario: ignore invalid format of command2
    Given a table width 5
    Then try to send "PLACE 0 0 NORTH"

  Scenario: ignore invalid command
    Given a table width 5
    Then try to dance

  Scenario: ignore invalid command after placed
    Given a table width 5
    And place at "0,0,NORTH"
    Then try to dance

  Scenario: valid command after invalid command
    Given a table width 5
    And place at "0,0,NORTH"
    Then try to dance
    And report as "0,0,NORTH"

  Scenario: valid command after two invalid commands
    Given a table width 5
    And place at "0,0,NORTH"
    Then try to dance
    Then try to send "JUMP 1,1,NORTH"
    And report as "0,0,NORTH"