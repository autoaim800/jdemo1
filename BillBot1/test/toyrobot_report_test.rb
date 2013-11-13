require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotReportTest < Test::Unit::TestCase

  # Called before every test method runs. Can be used
  # to set up fixture information.
  def setup
    @table = BillBot1::Table.new(5)
    @bot = BillBot1::ToyRobot.new(@table)
    @bot.command('PLACE 0,0,NORTH')
  end

  # Called after every test method runs. Can be used to tear
  # down fixture information.
  def teardown
    # Do nothing
  end

  def test_rpt0
    assert_equal('0,0,NORTH', @bot.command('REPORT'))
  end

  def test_m1
    @bot.command('MOVE')
    assert_equal('0,1,NORTH', @bot.command('REPORT'))
  end

  def test_r1
    @bot.command('RIGHT')
    assert_equal('0,0,EAST', @bot.command('REPORT'))
  end
end