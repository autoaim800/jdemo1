Feature: place toy robot

  toy robot should be able to be placed on a table
  all position out of the table should be ignored

  Scenario: placed at 00NORTH
    Given a table width 5
    When place at "0,0,NORTH"
    Then report as "0,0,NORTH"

  Scenario: placed at 11NORTH
    Given a table width 5
    When place at "1,1,NORTH"
    Then report as "1,1,NORTH"

  Scenario: replace after move from 00NORTH
    Given a table width 5
    When place at "0,0,NORTH"
    And manage to move
    And place at "0,0,NORTH"
    Then report as "0,0,NORTH"

  Scenario: replace after turn right from 00NORTH
    Given a table width 5
    When place at "0,0,NORTH"
    And turn right
    And place at "0,0,NORTH"
    Then report as "0,0,NORTH"

  Scenario: replace after turn left from 00NORTH
    Given a table width 5
    When place at "0,0,NORTH"
    And turn left
    And place at "0,0,NORTH"
    Then report as "0,0,NORTH"

  Scenario Outline: place at valid position
    Given a table width 5
    When place at "<position>"
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

  Scenario Outline: place at invalid position
    Given a table width 5
    When try to place at "<position>"
    Then try to report
    Examples: all invalid position
    | position |
    | -5,-5,EAST |
    | 5,5,EAST |
    | -5,-4,EAST |
    | 5,6,EAST |
    | -5,-3,EAST |
    | 5,7,EAST |
    | -5,-2,EAST |
    | 5,8,EAST |
    | -5,-1,EAST |
    | 5,9,EAST |
    | -4,-5,EAST |
    | 6,5,EAST |
    | -4,-4,EAST |
    | 6,6,EAST |
    | -4,-3,EAST |
    | 6,7,EAST |
    | -4,-2,EAST |
    | 6,8,EAST |
    | -4,-1,EAST |
    | 6,9,EAST |
    | -3,-5,EAST |
    | 7,5,EAST |
    | -3,-4,EAST |
    | 7,6,EAST |
    | -3,-3,EAST |
    | 7,7,EAST |
    | -3,-2,EAST |
    | 7,8,EAST |
    | -3,-1,EAST |
    | 7,9,EAST |
    | -2,-5,EAST |
    | 8,5,EAST |
    | -2,-4,EAST |
    | 8,6,EAST |
    | -2,-3,EAST |
    | 8,7,EAST |
    | -2,-2,EAST |
    | 8,8,EAST |
    | -2,-1,EAST |
    | 8,9,EAST |
    | -1,-5,EAST |
    | 9,5,EAST |
    | -1,-4,EAST |
    | 9,6,EAST |
    | -1,-3,EAST |
    | 9,7,EAST |
    | -1,-2,EAST |
    | 9,8,EAST |
    | -1,-1,EAST |
    | 9,9,EAST |
    | -5,-5,SOUTH |
    | 5,5,SOUTH |
    | -5,-4,SOUTH |
    | 5,6,SOUTH |
    | -5,-3,SOUTH |
    | 5,7,SOUTH |
    | -5,-2,SOUTH |
    | 5,8,SOUTH |
    | -5,-1,SOUTH |
    | 5,9,SOUTH |
    | -4,-5,SOUTH |
    | 6,5,SOUTH |
    | -4,-4,SOUTH |
    | 6,6,SOUTH |
    | -4,-3,SOUTH |
    | 6,7,SOUTH |
    | -4,-2,SOUTH |
    | 6,8,SOUTH |
    | -4,-1,SOUTH |
    | 6,9,SOUTH |
    | -3,-5,SOUTH |
    | 7,5,SOUTH |
    | -3,-4,SOUTH |
    | 7,6,SOUTH |
    | -3,-3,SOUTH |
    | 7,7,SOUTH |
    | -3,-2,SOUTH |
    | 7,8,SOUTH |
    | -3,-1,SOUTH |
    | 7,9,SOUTH |
    | -2,-5,SOUTH |
    | 8,5,SOUTH |
    | -2,-4,SOUTH |
    | 8,6,SOUTH |
    | -2,-3,SOUTH |
    | 8,7,SOUTH |
    | -2,-2,SOUTH |
    | 8,8,SOUTH |
    | -2,-1,SOUTH |
    | 8,9,SOUTH |
    | -1,-5,SOUTH |
    | 9,5,SOUTH |
    | -1,-4,SOUTH |
    | 9,6,SOUTH |
    | -1,-3,SOUTH |
    | 9,7,SOUTH |
    | -1,-2,SOUTH |
    | 9,8,SOUTH |
    | -1,-1,SOUTH |
    | 9,9,SOUTH |
    | -5,-5,WEST |
    | 5,5,WEST |
    | -5,-4,WEST |
    | 5,6,WEST |
    | -5,-3,WEST |
    | 5,7,WEST |
    | -5,-2,WEST |
    | 5,8,WEST |
    | -5,-1,WEST |
    | 5,9,WEST |
    | -4,-5,WEST |
    | 6,5,WEST |
    | -4,-4,WEST |
    | 6,6,WEST |
    | -4,-3,WEST |
    | 6,7,WEST |
    | -4,-2,WEST |
    | 6,8,WEST |
    | -4,-1,WEST |
    | 6,9,WEST |
    | -3,-5,WEST |
    | 7,5,WEST |
    | -3,-4,WEST |
    | 7,6,WEST |
    | -3,-3,WEST |
    | 7,7,WEST |
    | -3,-2,WEST |
    | 7,8,WEST |
    | -3,-1,WEST |
    | 7,9,WEST |
    | -2,-5,WEST |
    | 8,5,WEST |
    | -2,-4,WEST |
    | 8,6,WEST |
    | -2,-3,WEST |
    | 8,7,WEST |
    | -2,-2,WEST |
    | 8,8,WEST |
    | -2,-1,WEST |
    | 8,9,WEST |
    | -1,-5,WEST |
    | 9,5,WEST |
    | -1,-4,WEST |
    | 9,6,WEST |
    | -1,-3,WEST |
    | 9,7,WEST |
    | -1,-2,WEST |
    | 9,8,WEST |
    | -1,-1,WEST |
    | 9,9,WEST |
    | -5,-5,NORTH |
    | 5,5,NORTH |
    | -5,-4,NORTH |
    | 5,6,NORTH |
    | -5,-3,NORTH |
    | 5,7,NORTH |
    | -5,-2,NORTH |
    | 5,8,NORTH |
    | -5,-1,NORTH |
    | 5,9,NORTH |
    | -4,-5,NORTH |
    | 6,5,NORTH |
    | -4,-4,NORTH |
    | 6,6,NORTH |
    | -4,-3,NORTH |
    | 6,7,NORTH |
    | -4,-2,NORTH |
    | 6,8,NORTH |
    | -4,-1,NORTH |
    | 6,9,NORTH |
    | -3,-5,NORTH |
    | 7,5,NORTH |
    | -3,-4,NORTH |
    | 7,6,NORTH |
    | -3,-3,NORTH |
    | 7,7,NORTH |
    | -3,-2,NORTH |
    | 7,8,NORTH |
    | -3,-1,NORTH |
    | 7,9,NORTH |
    | -2,-5,NORTH |
    | 8,5,NORTH |
    | -2,-4,NORTH |
    | 8,6,NORTH |
    | -2,-3,NORTH |
    | 8,7,NORTH |
    | -2,-2,NORTH |
    | 8,8,NORTH |
    | -2,-1,NORTH |
    | 8,9,NORTH |
    | -1,-5,NORTH |
    | 9,5,NORTH |
    | -1,-4,NORTH |
    | 9,6,NORTH |
    | -1,-3,NORTH |
    | 9,7,NORTH |
    | -1,-2,NORTH |
    | 9,8,NORTH |
    | -1,-1,NORTH |
    | 9,9,NORTH |