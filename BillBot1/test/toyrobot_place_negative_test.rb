require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotPlaceNegativeTest < Test::Unit::TestCase

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
  def place_negative(dirs, range, msg)
      dirs.each() do |dir|
        range.each do |x|
          range.each do |y|
            cmd = "PLACE #{x},#{y},#{dir}"
            ret = @bot.command(cmd)
            assert_nil(ret, msg)
            assert_equal(false, @bot.placed)
          end
        end
      end
  end
  # Fake test
  def test_66n
    assert_nil(@bot.command('PLACE 6,6,NORTH'), 'should not be placed on invalid coord')
  end

  def test_66e
    assert_nil(@bot.command('PLACE 6,6,EAST'), 'should not be placed on invalid coord')
  end

  # out of boundary with correct facing
  def test_placed_66n
    dirs = %w(NORTH WEST EAST SOUTH SOUTH NORTH WEST)
    range = (5..10)
    place_negative(dirs, range, 'place bot with invalid position returns non-nil')
    range = (-5..-1)
    place_negative(dirs, range, 'place bot with invalid position returns non-nil')
  end

  # with in of boundary with incorrect facing
  def test_placed_55m
    dirs = %w(UP SOUTHWEST DOWN CENTER LEFT RIGHT NORTH_EAST)
    range = (0..4)
    place_negative(dirs, range, 'place bot with invalid position returns non-nil')
  end

  # out of boundary with incorrect facing
  def test_placed_66m
    dirs = %w(UP SOUTHWEST DOWN CENTER LEFT RIGHT NORTH_EAST)
    range = (6..10)
    place_negative(dirs, range, 'place bot with invalid position returns non-nil')
    range = (-5..-1)
    place_negative(dirs, range, 'place bot with invalid position returns non-nil')
  end

end