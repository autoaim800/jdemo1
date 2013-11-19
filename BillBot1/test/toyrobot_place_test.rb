require 'test/unit'
require_relative '../lib/BillBot1'

class ToyRobotPlaceTest < Test::Unit::TestCase

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
  def test_placed_00n
    ret = @bot.command('PLACE 0,0,NORTH')
    assert_not_nil(ret, 'place bot with valid position')
    assert_equal(0, @bot.x)
    assert_equal(0, @bot.y)
    assert_equal(:NORTH, @bot.facing)
    assert_equal(true, @bot.placed)
  end

  def test_placed_xyn
    dirs = %w(NORTH WEST EAST SOUTH SOUTH NORTH WEST)
    range = (0..4)
    place_positive(dirs, range, 'place bot with valid position returns nil')
  end

  def place_positive(dirs, range, msg)
    dirs.each() do |dir|
      dir_sym = dir.to_sym
      range.each do |x|
        range.each do |y|
          cmd = "PLACE #{x},#{y},#{dir}"
          ret = @bot.command(cmd)
          assert_not_nil(ret, msg)
          assert_equal(x, @bot.x)
          assert_equal(y, @bot.y)
          assert_equal(dir_sym, @bot.facing)
          assert_equal(true, @bot.placed)
        end
      end
    end
  end

  def test_00n_distance
    ret = @bot.command('PLACE 0,0,NORTH')
    assert_not_nil(ret, 'place bot with valid position returns nil')
    assert_equal(4, @bot.distance)
  end

  def test_00e_distance
    ret = @bot.command('PLACE 0,0,EAST')
    assert_not_nil(ret, 'place bot with valid position returns nil')
    assert_equal(4, @bot.distance)
  end

  def test_00w_distance
    ret = @bot.command('PLACE 0,0,WEST')
    assert_not_nil(ret, 'place bot with valid position')
    assert_equal(0, @bot.distance)
  end

  def test_00s_distance
    ret = @bot.command('PLACE 0,0,SOUTH')
    assert_not_nil(ret, 'place bot with valid position')
    assert_equal(0, @bot.distance)
  end

  def test_22x_distance
    dirs = %w(NORTH WEST EAST SOUTH SOUTH NORTH WEST)
    dirs.each do |dir|
      cmd = "PLACE 2,2,#{dir}"
      ret = @bot.command(cmd)
      assert_not_nil(ret, 'place bot with valid position')
      assert_equal(2, @bot.distance)
    end
  end
end