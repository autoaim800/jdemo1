require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotTurnTest < Test::Unit::TestCase

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

  def test_r1
    range = (0...10)
    range.each() do |x|
      n = 1 + x * 4
      @bot.command('PLACE 0,0,NORTH')
      n.times() { @bot.command('RIGHT') }
      assert_equal(:EAST, @bot.facing)
    end
  end

  def test_l1
    range = (0...10)
    range.each() do |x|
      n = 1 + x * 4
      @bot.command('PLACE 0,0,NORTH')
      n.times() { @bot.command('LEFT') }
      assert_equal(:WEST, @bot.facing)
    end
  end

  def test_r2
    2.times() { @bot.command('RIGHT') }
    assert_equal(:SOUTH, @bot.facing)
  end

  def test_r3
    3.times() { @bot.command('RIGHT') }
    assert_equal(:WEST, @bot.facing)
  end

  def test_r4
    4.times() { @bot.command('RIGHT') }
    assert_equal(:NORTH, @bot.facing)
  end
end