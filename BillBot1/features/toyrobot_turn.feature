Feature: turn toy robot

  toy robot should be able to turn to either right or left after placed
  should able to turn in iteration of EAST, SOUTH, WEST, NORTH

  Scenario: ignore turn right prior place
    Given a table width 5
    When try to turn right
    Then try to report

  Scenario: ignore turn right prior place
    Given a table width 5
    When try to turn left
    Then try to report

  Scenario: placed at 00NORTH then turn right
    Given a table width 5
    When place at "0,0,NORTH"
    And turn right
    When place at "0,0,EAST"

  Scenario: placed at 00NORTH then turn left
    Given a table width 5
    When place at "0,0,NORTH"
    And turn left
    When place at "0,0,WEST"

  Scenario: placed at 22NORTH then move and turn right
    Given a table width 5
    When place at "2,2,NORTH"
    And turn right
    And manage to move
    When place at "3,2,EAST"

  Scenario: placed at 22NORTH then move and turn left
    Given a table width 5
    When place at "2,2,NORTH"
    And turn left
    And manage to move
    When place at "1,2,WEST"

  Scenario: placed at 00NORTH then turn left twice
    Given a table width 5
    When place at "0,0,NORTH"
    And turn left
    And turn left
    When place at "0,0,SOUTH"

  Scenario Outline: place at nnN then turn left
    Given a table width 5
    When place at "<position1>"
    And turn left
    Then report as "<position2>"
    Examples: all valid position
      | position1| position2 |
      | 0,0,EAST | 0,0,NORTH |
      | 0,1,EAST | 0,1,NORTH |
      | 0,2,EAST | 0,2,NORTH |
      | 0,3,EAST | 0,3,NORTH |
      | 0,4,EAST | 0,4,NORTH |
      | 1,0,EAST | 1,0,NORTH |
      | 1,1,EAST | 1,1,NORTH |
      | 1,2,EAST | 1,2,NORTH |
      | 1,3,EAST | 1,3,NORTH |
      | 1,4,EAST | 1,4,NORTH |
      | 2,0,EAST | 2,0,NORTH |
      | 2,1,EAST | 2,1,NORTH |
      | 2,2,EAST | 2,2,NORTH |
      | 2,3,EAST | 2,3,NORTH |
      | 2,4,EAST | 2,4,NORTH |
      | 3,0,EAST | 3,0,NORTH |
      | 3,1,EAST | 3,1,NORTH |
      | 3,2,EAST | 3,2,NORTH |
      | 3,3,EAST | 3,3,NORTH |
      | 3,4,EAST | 3,4,NORTH |
      | 4,0,EAST | 4,0,NORTH |
      | 4,1,EAST | 4,1,NORTH |
      | 4,2,EAST | 4,2,NORTH |
      | 4,3,EAST | 4,3,NORTH |
      | 4,4,EAST | 4,4,NORTH |
      | 0,0,SOUTH | 0,0,EAST |
      | 0,1,SOUTH | 0,1,EAST |
      | 0,2,SOUTH | 0,2,EAST |
      | 0,3,SOUTH | 0,3,EAST |
      | 0,4,SOUTH | 0,4,EAST |
      | 1,0,SOUTH | 1,0,EAST |
      | 1,1,SOUTH | 1,1,EAST |
      | 1,2,SOUTH | 1,2,EAST |
      | 1,3,SOUTH | 1,3,EAST |
      | 1,4,SOUTH | 1,4,EAST |
      | 2,0,SOUTH | 2,0,EAST |
      | 2,1,SOUTH | 2,1,EAST |
      | 2,2,SOUTH | 2,2,EAST |
      | 2,3,SOUTH | 2,3,EAST |
      | 2,4,SOUTH | 2,4,EAST |
      | 3,0,SOUTH | 3,0,EAST |
      | 3,1,SOUTH | 3,1,EAST |
      | 3,2,SOUTH | 3,2,EAST |
      | 3,3,SOUTH | 3,3,EAST |
      | 3,4,SOUTH | 3,4,EAST |
      | 4,0,SOUTH | 4,0,EAST |
      | 4,1,SOUTH | 4,1,EAST |
      | 4,2,SOUTH | 4,2,EAST |
      | 4,3,SOUTH | 4,3,EAST |
      | 4,4,SOUTH | 4,4,EAST |
      | 0,0,WEST | 0,0,SOUTH |
      | 0,1,WEST | 0,1,SOUTH |
      | 0,2,WEST | 0,2,SOUTH |
      | 0,3,WEST | 0,3,SOUTH |
      | 0,4,WEST | 0,4,SOUTH |
      | 1,0,WEST | 1,0,SOUTH |
      | 1,1,WEST | 1,1,SOUTH |
      | 1,2,WEST | 1,2,SOUTH |
      | 1,3,WEST | 1,3,SOUTH |
      | 1,4,WEST | 1,4,SOUTH |
      | 2,0,WEST | 2,0,SOUTH |
      | 2,1,WEST | 2,1,SOUTH |
      | 2,2,WEST | 2,2,SOUTH |
      | 2,3,WEST | 2,3,SOUTH |
      | 2,4,WEST | 2,4,SOUTH |
      | 3,0,WEST | 3,0,SOUTH |
      | 3,1,WEST | 3,1,SOUTH |
      | 3,2,WEST | 3,2,SOUTH |
      | 3,3,WEST | 3,3,SOUTH |
      | 3,4,WEST | 3,4,SOUTH |
      | 4,0,WEST | 4,0,SOUTH |
      | 4,1,WEST | 4,1,SOUTH |
      | 4,2,WEST | 4,2,SOUTH |
      | 4,3,WEST | 4,3,SOUTH |
      | 4,4,WEST | 4,4,SOUTH |
      | 0,0,NORTH | 0,0,WEST |
      | 0,1,NORTH | 0,1,WEST |
      | 0,2,NORTH | 0,2,WEST |
      | 0,3,NORTH | 0,3,WEST |
      | 0,4,NORTH | 0,4,WEST |
      | 1,0,NORTH | 1,0,WEST |
      | 1,1,NORTH | 1,1,WEST |
      | 1,2,NORTH | 1,2,WEST |
      | 1,3,NORTH | 1,3,WEST |
      | 1,4,NORTH | 1,4,WEST |
      | 2,0,NORTH | 2,0,WEST |
      | 2,1,NORTH | 2,1,WEST |
      | 2,2,NORTH | 2,2,WEST |
      | 2,3,NORTH | 2,3,WEST |
      | 2,4,NORTH | 2,4,WEST |
      | 3,0,NORTH | 3,0,WEST |
      | 3,1,NORTH | 3,1,WEST |
      | 3,2,NORTH | 3,2,WEST |
      | 3,3,NORTH | 3,3,WEST |
      | 3,4,NORTH | 3,4,WEST |
      | 4,0,NORTH | 4,0,WEST |
      | 4,1,NORTH | 4,1,WEST |
      | 4,2,NORTH | 4,2,WEST |
      | 4,3,NORTH | 4,3,WEST |
      | 4,4,NORTH | 4,4,WEST |


  Scenario Outline: place at nnN then turn right
    Given a table width 5
    When place at "<position1>"
    And turn right
    Then report as "<position2>"
    Examples: all valid turn right
      | position1| position2 |
      | 0,0,EAST | 0,0,SOUTH |
      | 0,1,EAST | 0,1,SOUTH |
      | 0,2,EAST | 0,2,SOUTH |
      | 0,3,EAST | 0,3,SOUTH |
      | 0,4,EAST | 0,4,SOUTH |
      | 1,0,EAST | 1,0,SOUTH |
      | 1,1,EAST | 1,1,SOUTH |
      | 1,2,EAST | 1,2,SOUTH |
      | 1,3,EAST | 1,3,SOUTH |
      | 1,4,EAST | 1,4,SOUTH |
      | 2,0,EAST | 2,0,SOUTH |
      | 2,1,EAST | 2,1,SOUTH |
      | 2,2,EAST | 2,2,SOUTH |
      | 2,3,EAST | 2,3,SOUTH |
      | 2,4,EAST | 2,4,SOUTH |
      | 3,0,EAST | 3,0,SOUTH |
      | 3,1,EAST | 3,1,SOUTH |
      | 3,2,EAST | 3,2,SOUTH |
      | 3,3,EAST | 3,3,SOUTH |
      | 3,4,EAST | 3,4,SOUTH |
      | 4,0,EAST | 4,0,SOUTH |
      | 4,1,EAST | 4,1,SOUTH |
      | 4,2,EAST | 4,2,SOUTH |
      | 4,3,EAST | 4,3,SOUTH |
      | 4,4,EAST | 4,4,SOUTH |
      | 0,0,SOUTH | 0,0,WEST |
      | 0,1,SOUTH | 0,1,WEST |
      | 0,2,SOUTH | 0,2,WEST |
      | 0,3,SOUTH | 0,3,WEST |
      | 0,4,SOUTH | 0,4,WEST |
      | 1,0,SOUTH | 1,0,WEST |
      | 1,1,SOUTH | 1,1,WEST |
      | 1,2,SOUTH | 1,2,WEST |
      | 1,3,SOUTH | 1,3,WEST |
      | 1,4,SOUTH | 1,4,WEST |
      | 2,0,SOUTH | 2,0,WEST |
      | 2,1,SOUTH | 2,1,WEST |
      | 2,2,SOUTH | 2,2,WEST |
      | 2,3,SOUTH | 2,3,WEST |
      | 2,4,SOUTH | 2,4,WEST |
      | 3,0,SOUTH | 3,0,WEST |
      | 3,1,SOUTH | 3,1,WEST |
      | 3,2,SOUTH | 3,2,WEST |
      | 3,3,SOUTH | 3,3,WEST |
      | 3,4,SOUTH | 3,4,WEST |
      | 4,0,SOUTH | 4,0,WEST |
      | 4,1,SOUTH | 4,1,WEST |
      | 4,2,SOUTH | 4,2,WEST |
      | 4,3,SOUTH | 4,3,WEST |
      | 4,4,SOUTH | 4,4,WEST |
      | 0,0,WEST | 0,0,NORTH |
      | 0,1,WEST | 0,1,NORTH |
      | 0,2,WEST | 0,2,NORTH |
      | 0,3,WEST | 0,3,NORTH |
      | 0,4,WEST | 0,4,NORTH |
      | 1,0,WEST | 1,0,NORTH |
      | 1,1,WEST | 1,1,NORTH |
      | 1,2,WEST | 1,2,NORTH |
      | 1,3,WEST | 1,3,NORTH |
      | 1,4,WEST | 1,4,NORTH |
      | 2,0,WEST | 2,0,NORTH |
      | 2,1,WEST | 2,1,NORTH |
      | 2,2,WEST | 2,2,NORTH |
      | 2,3,WEST | 2,3,NORTH |
      | 2,4,WEST | 2,4,NORTH |
      | 3,0,WEST | 3,0,NORTH |
      | 3,1,WEST | 3,1,NORTH |
      | 3,2,WEST | 3,2,NORTH |
      | 3,3,WEST | 3,3,NORTH |
      | 3,4,WEST | 3,4,NORTH |
      | 4,0,WEST | 4,0,NORTH |
      | 4,1,WEST | 4,1,NORTH |
      | 4,2,WEST | 4,2,NORTH |
      | 4,3,WEST | 4,3,NORTH |
      | 4,4,WEST | 4,4,NORTH |
      | 0,0,NORTH | 0,0,EAST |
      | 0,1,NORTH | 0,1,EAST |
      | 0,2,NORTH | 0,2,EAST |
      | 0,3,NORTH | 0,3,EAST |
      | 0,4,NORTH | 0,4,EAST |
      | 1,0,NORTH | 1,0,EAST |
      | 1,1,NORTH | 1,1,EAST |
      | 1,2,NORTH | 1,2,EAST |
      | 1,3,NORTH | 1,3,EAST |
      | 1,4,NORTH | 1,4,EAST |
      | 2,0,NORTH | 2,0,EAST |
      | 2,1,NORTH | 2,1,EAST |
      | 2,2,NORTH | 2,2,EAST |
      | 2,3,NORTH | 2,3,EAST |
      | 2,4,NORTH | 2,4,EAST |
      | 3,0,NORTH | 3,0,EAST |
      | 3,1,NORTH | 3,1,EAST |
      | 3,2,NORTH | 3,2,EAST |
      | 3,3,NORTH | 3,3,EAST |
      | 3,4,NORTH | 3,4,EAST |
      | 4,0,NORTH | 4,0,EAST |
      | 4,1,NORTH | 4,1,EAST |
      | 4,2,NORTH | 4,2,EAST |
      | 4,3,NORTH | 4,3,EAST |
      | 4,4,NORTH | 4,4,EAST |


  Scenario Outline: place at nnN then turn left fourth
    Given a table width 5
    When place at "<position>"
    And turn left
    And turn left
    And turn left
    And turn left
    Then report as "<position>"
    Examples: all valid position
      | position |
      | 0,0,EAST |
      | 0,1,EAST |
      | 0,2,EAST |
      | 0,3,EAST |
      | 0,4,EAST |
      | 1,0,EAST |
      | 1,1,EAST |
      | 1,2,EAST |
      | 1,3,EAST |
      | 1,4,EAST |
      | 2,0,EAST |
      | 2,1,EAST |
      | 2,2,EAST |
      | 2,3,EAST |
      | 2,4,EAST |
      | 3,0,EAST |
      | 3,1,EAST |
      | 3,2,EAST |
      | 3,3,EAST |
      | 3,4,EAST |
      | 4,0,EAST |
      | 4,1,EAST |
      | 4,2,EAST |
      | 4,3,EAST |
      | 4,4,EAST |
      | 0,0,SOUTH |
      | 0,1,SOUTH |
      | 0,2,SOUTH |
      | 0,3,SOUTH |
      | 0,4,SOUTH |
      | 1,0,SOUTH |
      | 1,1,SOUTH |
      | 1,2,SOUTH |
      | 1,3,SOUTH |
      | 1,4,SOUTH |
      | 2,0,SOUTH |
      | 2,1,SOUTH |
      | 2,2,SOUTH |
      | 2,3,SOUTH |
      | 2,4,SOUTH |
      | 3,0,SOUTH |
      | 3,1,SOUTH |
      | 3,2,SOUTH |
      | 3,3,SOUTH |
      | 3,4,SOUTH |
      | 4,0,SOUTH |
      | 4,1,SOUTH |
      | 4,2,SOUTH |
      | 4,3,SOUTH |
      | 4,4,SOUTH |
      | 0,0,WEST |
      | 0,1,WEST |
      | 0,2,WEST |
      | 0,3,WEST |
      | 0,4,WEST |
      | 1,0,WEST |
      | 1,1,WEST |
      | 1,2,WEST |
      | 1,3,WEST |
      | 1,4,WEST |
      | 2,0,WEST |
      | 2,1,WEST |
      | 2,2,WEST |
      | 2,3,WEST |
      | 2,4,WEST |
      | 3,0,WEST |
      | 3,1,WEST |
      | 3,2,WEST |
      | 3,3,WEST |
      | 3,4,WEST |
      | 4,0,WEST |
      | 4,1,WEST |
      | 4,2,WEST |
      | 4,3,WEST |
      | 4,4,WEST |
      | 0,0,NORTH |
      | 0,1,NORTH |
      | 0,2,NORTH |
      | 0,3,NORTH |
      | 0,4,NORTH |
      | 1,0,NORTH |
      | 1,1,NORTH |
      | 1,2,NORTH |
      | 1,3,NORTH |
      | 1,4,NORTH |
      | 2,0,NORTH |
      | 2,1,NORTH |
      | 2,2,NORTH |
      | 2,3,NORTH |
      | 2,4,NORTH |
      | 3,0,NORTH |
      | 3,1,NORTH |
      | 3,2,NORTH |
      | 3,3,NORTH |
      | 3,4,NORTH |
      | 4,0,NORTH |
      | 4,1,NORTH |
      | 4,2,NORTH |
      | 4,3,NORTH |
      | 4,4,NORTH |

  Scenario Outline: place at nnN then turn right fourth
    Given a table width 5
    When place at "<position>"
    And turn right
    And turn right
    And turn right
    And turn right
    Then report as "<position>"
  Examples: all valid position
    | position |
    | 0,0,EAST |
    | 0,1,EAST |
    | 0,2,EAST |
    | 0,3,EAST |
    | 0,4,EAST |
    | 1,0,EAST |
    | 1,1,EAST |
    | 1,2,EAST |
    | 1,3,EAST |
    | 1,4,EAST |
    | 2,0,EAST |
    | 2,1,EAST |
    | 2,2,EAST |
    | 2,3,EAST |
    | 2,4,EAST |
    | 3,0,EAST |
    | 3,1,EAST |
    | 3,2,EAST |
    | 3,3,EAST |
    | 3,4,EAST |
    | 4,0,EAST |
    | 4,1,EAST |
    | 4,2,EAST |
    | 4,3,EAST |
    | 4,4,EAST |
    | 0,0,SOUTH |
    | 0,1,SOUTH |
    | 0,2,SOUTH |
    | 0,3,SOUTH |
    | 0,4,SOUTH |
    | 1,0,SOUTH |
    | 1,1,SOUTH |
    | 1,2,SOUTH |
    | 1,3,SOUTH |
    | 1,4,SOUTH |
    | 2,0,SOUTH |
    | 2,1,SOUTH |
    | 2,2,SOUTH |
    | 2,3,SOUTH |
    | 2,4,SOUTH |
    | 3,0,SOUTH |
    | 3,1,SOUTH |
    | 3,2,SOUTH |
    | 3,3,SOUTH |
    | 3,4,SOUTH |
    | 4,0,SOUTH |
    | 4,1,SOUTH |
    | 4,2,SOUTH |
    | 4,3,SOUTH |
    | 4,4,SOUTH |
    | 0,0,WEST |
    | 0,1,WEST |
    | 0,2,WEST |
    | 0,3,WEST |
    | 0,4,WEST |
    | 1,0,WEST |
    | 1,1,WEST |
    | 1,2,WEST |
    | 1,3,WEST |
    | 1,4,WEST |
    | 2,0,WEST |
    | 2,1,WEST |
    | 2,2,WEST |
    | 2,3,WEST |
    | 2,4,WEST |
    | 3,0,WEST |
    | 3,1,WEST |
    | 3,2,WEST |
    | 3,3,WEST |
    | 3,4,WEST |
    | 4,0,WEST |
    | 4,1,WEST |
    | 4,2,WEST |
    | 4,3,WEST |
    | 4,4,WEST |
    | 0,0,NORTH |
    | 0,1,NORTH |
    | 0,2,NORTH |
    | 0,3,NORTH |
    | 0,4,NORTH |
    | 1,0,NORTH |
    | 1,1,NORTH |
    | 1,2,NORTH |
    | 1,3,NORTH |
    | 1,4,NORTH |
    | 2,0,NORTH |
    | 2,1,NORTH |
    | 2,2,NORTH |
    | 2,3,NORTH |
    | 2,4,NORTH |
    | 3,0,NORTH |
    | 3,1,NORTH |
    | 3,2,NORTH |
    | 3,3,NORTH |
    | 3,4,NORTH |
    | 4,0,NORTH |
    | 4,1,NORTH |
    | 4,2,NORTH |
    | 4,3,NORTH |
    | 4,4,NORTH |

  Scenario Outline: place at nnN then turn right twice
    Given a table width 5
    When place at "<position1>"
    And turn right
    And turn right
    Then report as "<position2>"
    Examples:
      |position1 | position2|
      | 0,0,EAST | 0,0,WEST |
      | 0,1,EAST | 0,1,WEST |
      | 0,2,EAST | 0,2,WEST |
      | 0,3,EAST | 0,3,WEST |
      | 0,4,EAST | 0,4,WEST |
      | 1,0,EAST | 1,0,WEST |
      | 1,1,EAST | 1,1,WEST |
      | 1,2,EAST | 1,2,WEST |
      | 1,3,EAST | 1,3,WEST |
      | 1,4,EAST | 1,4,WEST |
      | 2,0,EAST | 2,0,WEST |
      | 2,1,EAST | 2,1,WEST |
      | 2,2,EAST | 2,2,WEST |
      | 2,3,EAST | 2,3,WEST |
      | 2,4,EAST | 2,4,WEST |
      | 3,0,EAST | 3,0,WEST |
      | 3,1,EAST | 3,1,WEST |
      | 3,2,EAST | 3,2,WEST |
      | 3,3,EAST | 3,3,WEST |
      | 3,4,EAST | 3,4,WEST |
      | 4,0,EAST | 4,0,WEST |
      | 4,1,EAST | 4,1,WEST |
      | 4,2,EAST | 4,2,WEST |
      | 4,3,EAST | 4,3,WEST |
      | 4,4,EAST | 4,4,WEST |
      | 0,0,SOUTH | 0,0,NORTH |
      | 0,1,SOUTH | 0,1,NORTH |
      | 0,2,SOUTH | 0,2,NORTH |
      | 0,3,SOUTH | 0,3,NORTH |
      | 0,4,SOUTH | 0,4,NORTH |
      | 1,0,SOUTH | 1,0,NORTH |
      | 1,1,SOUTH | 1,1,NORTH |
      | 1,2,SOUTH | 1,2,NORTH |
      | 1,3,SOUTH | 1,3,NORTH |
      | 1,4,SOUTH | 1,4,NORTH |
      | 2,0,SOUTH | 2,0,NORTH |
      | 2,1,SOUTH | 2,1,NORTH |
      | 2,2,SOUTH | 2,2,NORTH |
      | 2,3,SOUTH | 2,3,NORTH |
      | 2,4,SOUTH | 2,4,NORTH |
      | 3,0,SOUTH | 3,0,NORTH |
      | 3,1,SOUTH | 3,1,NORTH |
      | 3,2,SOUTH | 3,2,NORTH |
      | 3,3,SOUTH | 3,3,NORTH |
      | 3,4,SOUTH | 3,4,NORTH |
      | 4,0,SOUTH | 4,0,NORTH |
      | 4,1,SOUTH | 4,1,NORTH |
      | 4,2,SOUTH | 4,2,NORTH |
      | 4,3,SOUTH | 4,3,NORTH |
      | 4,4,SOUTH | 4,4,NORTH |
      | 0,0,WEST | 0,0,EAST |
      | 0,1,WEST | 0,1,EAST |
      | 0,2,WEST | 0,2,EAST |
      | 0,3,WEST | 0,3,EAST |
      | 0,4,WEST | 0,4,EAST |
      | 1,0,WEST | 1,0,EAST |
      | 1,1,WEST | 1,1,EAST |
      | 1,2,WEST | 1,2,EAST |
      | 1,3,WEST | 1,3,EAST |
      | 1,4,WEST | 1,4,EAST |
      | 2,0,WEST | 2,0,EAST |
      | 2,1,WEST | 2,1,EAST |
      | 2,2,WEST | 2,2,EAST |
      | 2,3,WEST | 2,3,EAST |
      | 2,4,WEST | 2,4,EAST |
      | 3,0,WEST | 3,0,EAST |
      | 3,1,WEST | 3,1,EAST |
      | 3,2,WEST | 3,2,EAST |
      | 3,3,WEST | 3,3,EAST |
      | 3,4,WEST | 3,4,EAST |
      | 4,0,WEST | 4,0,EAST |
      | 4,1,WEST | 4,1,EAST |
      | 4,2,WEST | 4,2,EAST |
      | 4,3,WEST | 4,3,EAST |
      | 4,4,WEST | 4,4,EAST |
      | 0,0,NORTH | 0,0,SOUTH |
      | 0,1,NORTH | 0,1,SOUTH |
      | 0,2,NORTH | 0,2,SOUTH |
      | 0,3,NORTH | 0,3,SOUTH |
      | 0,4,NORTH | 0,4,SOUTH |
      | 1,0,NORTH | 1,0,SOUTH |
      | 1,1,NORTH | 1,1,SOUTH |
      | 1,2,NORTH | 1,2,SOUTH |
      | 1,3,NORTH | 1,3,SOUTH |
      | 1,4,NORTH | 1,4,SOUTH |
      | 2,0,NORTH | 2,0,SOUTH |
      | 2,1,NORTH | 2,1,SOUTH |
      | 2,2,NORTH | 2,2,SOUTH |
      | 2,3,NORTH | 2,3,SOUTH |
      | 2,4,NORTH | 2,4,SOUTH |
      | 3,0,NORTH | 3,0,SOUTH |
      | 3,1,NORTH | 3,1,SOUTH |
      | 3,2,NORTH | 3,2,SOUTH |
      | 3,3,NORTH | 3,3,SOUTH |
      | 3,4,NORTH | 3,4,SOUTH |
      | 4,0,NORTH | 4,0,SOUTH |
      | 4,1,NORTH | 4,1,SOUTH |
      | 4,2,NORTH | 4,2,SOUTH |
      | 4,3,NORTH | 4,3,SOUTH |
      | 4,4,NORTH | 4,4,SOUTH |

  Scenario Outline: place at nnN then turn right twice
    Given a table width 5
    When place at "<position1>"
    And turn right
    And turn right
    Then report as "<position2>"
    Examples:
    | position1 | position2 |
    | 0,0,EAST | 0,0,WEST |
    | 0,1,EAST | 0,1,WEST |
    | 0,2,EAST | 0,2,WEST |
    | 0,3,EAST | 0,3,WEST |
    | 0,4,EAST | 0,4,WEST |
    | 1,0,EAST | 1,0,WEST |
    | 1,1,EAST | 1,1,WEST |
    | 1,2,EAST | 1,2,WEST |
    | 1,3,EAST | 1,3,WEST |
    | 1,4,EAST | 1,4,WEST |
    | 2,0,EAST | 2,0,WEST |
    | 2,1,EAST | 2,1,WEST |
    | 2,2,EAST | 2,2,WEST |
    | 2,3,EAST | 2,3,WEST |
    | 2,4,EAST | 2,4,WEST |
    | 3,0,EAST | 3,0,WEST |
    | 3,1,EAST | 3,1,WEST |
    | 3,2,EAST | 3,2,WEST |
    | 3,3,EAST | 3,3,WEST |
    | 3,4,EAST | 3,4,WEST |
    | 4,0,EAST | 4,0,WEST |
    | 4,1,EAST | 4,1,WEST |
    | 4,2,EAST | 4,2,WEST |
    | 4,3,EAST | 4,3,WEST |
    | 4,4,EAST | 4,4,WEST |
    | 0,0,SOUTH | 0,0,NORTH |
    | 0,1,SOUTH | 0,1,NORTH |
    | 0,2,SOUTH | 0,2,NORTH |
    | 0,3,SOUTH | 0,3,NORTH |
    | 0,4,SOUTH | 0,4,NORTH |
    | 1,0,SOUTH | 1,0,NORTH |
    | 1,1,SOUTH | 1,1,NORTH |
    | 1,2,SOUTH | 1,2,NORTH |
    | 1,3,SOUTH | 1,3,NORTH |
    | 1,4,SOUTH | 1,4,NORTH |
    | 2,0,SOUTH | 2,0,NORTH |
    | 2,1,SOUTH | 2,1,NORTH |
    | 2,2,SOUTH | 2,2,NORTH |
    | 2,3,SOUTH | 2,3,NORTH |
    | 2,4,SOUTH | 2,4,NORTH |
    | 3,0,SOUTH | 3,0,NORTH |
    | 3,1,SOUTH | 3,1,NORTH |
    | 3,2,SOUTH | 3,2,NORTH |
    | 3,3,SOUTH | 3,3,NORTH |
    | 3,4,SOUTH | 3,4,NORTH |
    | 4,0,SOUTH | 4,0,NORTH |
    | 4,1,SOUTH | 4,1,NORTH |
    | 4,2,SOUTH | 4,2,NORTH |
    | 4,3,SOUTH | 4,3,NORTH |
    | 4,4,SOUTH | 4,4,NORTH |
    | 0,0,WEST | 0,0,EAST |
    | 0,1,WEST | 0,1,EAST |
    | 0,2,WEST | 0,2,EAST |
    | 0,3,WEST | 0,3,EAST |
    | 0,4,WEST | 0,4,EAST |
    | 1,0,WEST | 1,0,EAST |
    | 1,1,WEST | 1,1,EAST |
    | 1,2,WEST | 1,2,EAST |
    | 1,3,WEST | 1,3,EAST |
    | 1,4,WEST | 1,4,EAST |
    | 2,0,WEST | 2,0,EAST |
    | 2,1,WEST | 2,1,EAST |
    | 2,2,WEST | 2,2,EAST |
    | 2,3,WEST | 2,3,EAST |
    | 2,4,WEST | 2,4,EAST |
    | 3,0,WEST | 3,0,EAST |
    | 3,1,WEST | 3,1,EAST |
    | 3,2,WEST | 3,2,EAST |
    | 3,3,WEST | 3,3,EAST |
    | 3,4,WEST | 3,4,EAST |
    | 4,0,WEST | 4,0,EAST |
    | 4,1,WEST | 4,1,EAST |
    | 4,2,WEST | 4,2,EAST |
    | 4,3,WEST | 4,3,EAST |
    | 4,4,WEST | 4,4,EAST |
    | 0,0,NORTH | 0,0,SOUTH |
    | 0,1,NORTH | 0,1,SOUTH |
    | 0,2,NORTH | 0,2,SOUTH |
    | 0,3,NORTH | 0,3,SOUTH |
    | 0,4,NORTH | 0,4,SOUTH |
    | 1,0,NORTH | 1,0,SOUTH |
    | 1,1,NORTH | 1,1,SOUTH |
    | 1,2,NORTH | 1,2,SOUTH |
    | 1,3,NORTH | 1,3,SOUTH |
    | 1,4,NORTH | 1,4,SOUTH |
    | 2,0,NORTH | 2,0,SOUTH |
    | 2,1,NORTH | 2,1,SOUTH |
    | 2,2,NORTH | 2,2,SOUTH |
    | 2,3,NORTH | 2,3,SOUTH |
    | 2,4,NORTH | 2,4,SOUTH |
    | 3,0,NORTH | 3,0,SOUTH |
    | 3,1,NORTH | 3,1,SOUTH |
    | 3,2,NORTH | 3,2,SOUTH |
    | 3,3,NORTH | 3,3,SOUTH |
    | 3,4,NORTH | 3,4,SOUTH |
    | 4,0,NORTH | 4,0,SOUTH |
    | 4,1,NORTH | 4,1,SOUTH |
    | 4,2,NORTH | 4,2,SOUTH |
    | 4,3,NORTH | 4,3,SOUTH |
    | 4,4,NORTH | 4,4,SOUTH |
