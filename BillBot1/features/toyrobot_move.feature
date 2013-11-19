Feature: move toy robot

  toy robot should be able to move after placed
  should be able to ignore move prior place
  should be able to ignore move at boundary

  Scenario: ignore move prior placed
    Given a table width 5
    When try to move
    Then try to report

  Scenario: move after placed
    Given a table width 5
    And place at "0,0,NORTH"
    When manage to move
    Then report as "0,1,NORTH"

  Scenario: move after placed and turn left
    Given a table width 5
    And place at "2,2,NORTH"
    When manage to move
    And turn left
    Then report as "2,3,WEST"

  Scenario: move after placed and turn right
    Given a table width 5
    And place at "2,2,NORTH"
    When manage to move
    And turn right
    Then report as "2,3,EAST"

  Scenario Outline: place at nnN then move
    Given a table width 5
    When place at "<position1>"
    And manage to move
    Then report as "<position2>"
    Examples: all valid position
      | position1 | position2 |
      | 0,0,EAST | 1,0,EAST |
      | 0,1,EAST | 1,1,EAST |
      | 0,2,EAST | 1,2,EAST |
      | 0,3,EAST | 1,3,EAST |
      | 0,4,EAST | 1,4,EAST |
      | 1,0,EAST | 2,0,EAST |
      | 1,1,EAST | 2,1,EAST |
      | 1,2,EAST | 2,2,EAST |
      | 1,3,EAST | 2,3,EAST |
      | 1,4,EAST | 2,4,EAST |
      | 2,0,EAST | 3,0,EAST |
      | 2,1,EAST | 3,1,EAST |
      | 2,2,EAST | 3,2,EAST |
      | 2,3,EAST | 3,3,EAST |
      | 2,4,EAST | 3,4,EAST |
      | 3,0,EAST | 4,0,EAST |
      | 3,1,EAST | 4,1,EAST |
      | 3,2,EAST | 4,2,EAST |
      | 3,3,EAST | 4,3,EAST |
      | 3,4,EAST | 4,4,EAST |
      | 0,1,SOUTH | 0,0,SOUTH |
      | 0,2,SOUTH | 0,1,SOUTH |
      | 0,3,SOUTH | 0,2,SOUTH |
      | 0,4,SOUTH | 0,3,SOUTH |
      | 1,1,SOUTH | 1,0,SOUTH |
      | 1,2,SOUTH | 1,1,SOUTH |
      | 1,3,SOUTH | 1,2,SOUTH |
      | 1,4,SOUTH | 1,3,SOUTH |
      | 2,1,SOUTH | 2,0,SOUTH |
      | 2,2,SOUTH | 2,1,SOUTH |
      | 2,3,SOUTH | 2,2,SOUTH |
      | 2,4,SOUTH | 2,3,SOUTH |
      | 3,1,SOUTH | 3,0,SOUTH |
      | 3,2,SOUTH | 3,1,SOUTH |
      | 3,3,SOUTH | 3,2,SOUTH |
      | 3,4,SOUTH | 3,3,SOUTH |
      | 4,1,SOUTH | 4,0,SOUTH |
      | 4,2,SOUTH | 4,1,SOUTH |
      | 4,3,SOUTH | 4,2,SOUTH |
      | 4,4,SOUTH | 4,3,SOUTH |
      | 1,0,WEST | 0,0,WEST |
      | 1,1,WEST | 0,1,WEST |
      | 1,2,WEST | 0,2,WEST |
      | 1,3,WEST | 0,3,WEST |
      | 1,4,WEST | 0,4,WEST |
      | 2,0,WEST | 1,0,WEST |
      | 2,1,WEST | 1,1,WEST |
      | 2,2,WEST | 1,2,WEST |
      | 2,3,WEST | 1,3,WEST |
      | 2,4,WEST | 1,4,WEST |
      | 3,0,WEST | 2,0,WEST |
      | 3,1,WEST | 2,1,WEST |
      | 3,2,WEST | 2,2,WEST |
      | 3,3,WEST | 2,3,WEST |
      | 3,4,WEST | 2,4,WEST |
      | 4,0,WEST | 3,0,WEST |
      | 4,1,WEST | 3,1,WEST |
      | 4,2,WEST | 3,2,WEST |
      | 4,3,WEST | 3,3,WEST |
      | 4,4,WEST | 3,4,WEST |
      | 0,0,NORTH | 0,1,NORTH |
      | 0,1,NORTH | 0,2,NORTH |
      | 0,2,NORTH | 0,3,NORTH |
      | 0,3,NORTH | 0,4,NORTH |
      | 1,0,NORTH | 1,1,NORTH |
      | 1,1,NORTH | 1,2,NORTH |
      | 1,2,NORTH | 1,3,NORTH |
      | 1,3,NORTH | 1,4,NORTH |
      | 2,0,NORTH | 2,1,NORTH |
      | 2,1,NORTH | 2,2,NORTH |
      | 2,2,NORTH | 2,3,NORTH |
      | 2,3,NORTH | 2,4,NORTH |
      | 3,0,NORTH | 3,1,NORTH |
      | 3,1,NORTH | 3,2,NORTH |
      | 3,2,NORTH | 3,3,NORTH |
      | 3,3,NORTH | 3,4,NORTH |
      | 4,0,NORTH | 4,1,NORTH |
      | 4,1,NORTH | 4,2,NORTH |
      | 4,2,NORTH | 4,3,NORTH |
      | 4,3,NORTH | 4,4,NORTH |


  Scenario Outline: ignore move at/towards boundary
    Given a table width 5
    When place at "<position>"
    And try to move
    Then report as "<position>"
    Examples: all boundary position
      | position |
      | 4,0,EAST |
      | 4,1,EAST |
      | 4,2,EAST |
      | 4,3,EAST |
      | 4,4,EAST |
      | 0,0,SOUTH |
      | 1,0,SOUTH |
      | 2,0,SOUTH |
      | 3,0,SOUTH |
      | 0,0,WEST |
      | 0,1,WEST |
      | 0,2,WEST |
      | 0,3,WEST |
      | 0,4,NORTH |
      | 1,4,NORTH |
      | 2,4,NORTH |
      | 3,4,NORTH |