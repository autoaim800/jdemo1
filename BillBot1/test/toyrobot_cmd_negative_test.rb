require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotCmdNegativeTest < Test::Unit::TestCase

  # Called before every test method runs. Can be used
  # to set up fixture information.
  def setup
    @table = BillBot1::Table.new(5)
    @bot = BillBot1::ToyRobot.new(@table)
    assert_equal(false, @bot.placed)
  end

  # Called after every test method runs. Can be used to tear
  # down fixture information.

  def teardown
    # Do nothing
  end


  def test_u1
    assert_nil(@bot.command('ABC'), 'ignore cmd returns non-nil')
  end

  def test_place_invalid_format1
    assert_nil(@bot.command('PLACE 0,0'), 'ignore cmd returns non-nil')
  end

  def test_place_invalid_format2
    assert_nil(@bot.command('PLACE'), 'ignore cmd returns non-nil')
  end

  def test_place_invalid_format3
    assert_nil(@bot.command('PLACE 0,0,0,NORTH'), 'ignore cmd returns non-nil')
  end

  def test_place_invalid_format4
    assert_nil(@bot.command('PLACE 0 0 NORTH'), 'ignore cmd returns non-nil')
  end
end