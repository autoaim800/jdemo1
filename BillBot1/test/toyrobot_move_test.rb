require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotMoveTest < Test::Unit::TestCase

  # Called before every test method runs. Can be used
  # to set up fixture information.
  def setup
    @table = BillBot1::Table.new(5)
    @bot = BillBot1::ToyRobot.new(@table)
    ret = @bot.command('PLACE 0,0,NORTH')
    assert_not_nil(ret, 'place bot with valid position')
    assert_equal(0, @bot.x)
    assert_equal(0, @bot.y)
    assert_equal(:NORTH, @bot.facing)
    assert_equal(true, @bot.placed)
  end

  # Called after every test method runs. Can be used to tear
  # down fixture information.
  def teardown
    # Do nothing
  end

  def test_m1
    ret = @bot.command('MOVE')
    assert_not_nil(ret, '00n m1')
    assert_equal(0, @bot.x, 'wrong x-coord after 00n m1')
    assert_equal(1, @bot.y, 'wrong y-coord after 00n m1')
    assert_equal(3, @bot.distance, 'wrong distance after 00n m1')
    assert_equal(:NORTH, @bot.facing, 'wrong facing after 00n m1')
  end
end