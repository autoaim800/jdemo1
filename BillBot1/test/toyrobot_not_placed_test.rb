require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotNotPlacedTest < Test::Unit::TestCase

  # Called before every test method runs. Can be used
  # to set up fixture information.
  def setup
    @table = BillBot1::Table.new(5)
    @bot = BillBot1::ToyRobot.new(@table)
  end

  # Called after every test method runs. Can be used to tear
  # down fixture information.

  def teardown
    # Do nothing
  end

  # Fake test
  def test_m1
    assert_nil(@bot.command('MOVE'), 'move returns non-nil when not placed')
  end

  def test_report
    assert_nil(@bot.command('REPORT'), 'report returns non-nil when not placed')
  end

  def test_left
    assert_nil(@bot.command('LEFT'), 'left returns non-nil when not placed')
  end

  def test_right
    assert_nil(@bot.command('RIGHT'), 'right returns non-nil when not placed')
  end

  def test_place
    assert_not_nil(@bot.command('PLACE 1,1,NORTH'), 'place returns nil when not placed')
  end

  def test_unknown_cmd
    assert_nil(@bot.command('DROP 1,1,NORTH'), 'place returns nil when not placed')
  end
end