require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotNegativeTest < Test::Unit::TestCase

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


  def test_m1
    assert_nil(@bot.command('MOVE'), 'wrong move when not placed')
  end

  def test_m3
    3.times { assert_nil(@bot.command('MOVE'), 'wrong move when not placed') }
  end

  def test_m5
    5.times { assert_nil(@bot.command('MOVE'), 'wrong move when not placed')}
  end

  def test_r1
    assert_nil(@bot.command('RIGHT'), 'wrong turn-right when not placed')
  end

  def test_r2
    2.times() { assert_nil(@bot.command('RIGHT'), 'wrong turn-right when not placed') }
  end

  def test_l1
    assert_nil(@bot.command('LEFT'), 'wrong turn-left when not placed')
  end

  def test_l2
    2.times() { assert_nil(@bot.command('LEFT'), 'wrong turn-left when not placed') }
  end
end