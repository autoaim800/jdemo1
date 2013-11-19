#require 'BillBot1/version'

# Bill's ToyRobot
# author:: Bill Minglei Zhang (mailto:autoaim800@gmail.com)
module BillBot1

  # ToyRobot clazz, contains public methods and main logic
  # read only properties: x, y, facing, distance and placed
  class ToyRobot

    #attribute for debugging purpose only
    attr_reader :x, :y, :facing, :distance, :placed

    ORIENTATIONS = [:EAST, :SOUTH, :WEST, :NORTH]
    COMMANDS = [:PLACE, :MOVE, :LEFT, :RIGHT, :REPORT]
    MOVES = [[1, 0], [0, -1], [-1, 0], [0, 1]]

    # robot must be initialized/assigned to a table
    def initialize(table)
      @table = table
      @x = -1
      @y = -1
      @facing = :EAST
      @placed = false
      @distance = 0
    end

    # public method, accepts a line of command
    def command(cmd_line)
      # puts("command() " + cmd_line)
      tokens = cmd_line.split(/\s+/)
      return nil if tokens.length < 1
      cmd = tokens.first.to_sym
      params = tokens.last
      if COMMANDS.include?(cmd)
        case cmd
          when :PLACE
            place(params)
          when :LEFT
            left()
          when :RIGHT
            right()
          when :MOVE
            move()
          when :REPORT
            report()
          else
            nil
        end
      end
    end

    private

    def report
      if @placed
        ret = "#{@x},#{@y},#{@facing}"
        puts ret
        ret
      else
        nil
      end
    end

    # private method turns the robot to its left.
    # only work if the robot is placed.
    # returns nil if the command is ignored.
    def left
      if @placed
        index = ORIENTATIONS.index(@facing)
        index -= 1
        index %= 4
        @facing = ORIENTATIONS[index]
        calc_distance()
        @facing
      else
        nil
      end
    end

    # private method turns robot to its right
    # returns nil if the command is ignored
    def right
      if @placed
        index = ORIENTATIONS.index(@facing)
        index += 1
        index %= 4
        @facing = ORIENTATIONS[index]
        calc_distance()
        @facing
      else
        nil
      end
    end

    # private move method
    # returns the remaining distance to the boundary
    # or returns nil if the move command has been ignored
    def move
      if @placed && @distance > 0
        index = ORIENTATIONS.index(@facing)
        moves = MOVES[index]
        @x = @x + moves[0]
        @y = @y + moves[1]
        @distance -= 1
      else
        nil
      end
    end

    # accepts parameter of PLACE command
    # returns nil if the command is ignored
    # returns 'placed' if the command is executed
    def place(params)
      tokens = params.split(/,/)

      return nil if tokens.length < 3

      x = tokens[0].to_i
      y = tokens[1].to_i
      face = tokens[2]
      return nil if face == nil || face.length < 1

      face = face.upcase.to_sym

      if valid_coord?(x, y) && valid_facing?(face)
        @x = x
        @y = y
        @facing = face
        @distance = calc_distance()
        @placed = true
        'placed'
      else
        nil
      end
    end

    # returns true if given coordinates are within the table
    def valid_coord?(x, y)
      x >= 0 && x < @table.width && y >= 0 && y< @table.width
    end

    # returns true if given face is valid
    def valid_facing?(face)
      ORIENTATIONS.include?(face)
    end

    # calculate distance from bot position to current boundary
    def calc_distance
      #index = ORIENTATIONS.index(@facing)
      case @facing
        when :EAST
          @table.width - @x - 1
        when :SOUTH
          @y
        when :WEST
          @x
        when :NORTH
          @table.width - @y - 1
        else
          0
      end
    end
  end

  # class hold only square-width of the table
  class Table

    attr_reader :width

    # @param width an integer of table square-width
    def initialize(width)
      @width = width
    end
  end

  # Toy Robot Runner accepts stdin or filename as input
  class ToyRobotRunner

    def initialize
      @table = Table.new(5)
      @bot = ToyRobot.new(@table)
    end

    #execute a line of command
    def run_line(cmd_line)
      @bot.command(cmd_line)
    end

    # execute commands from input file-path-name
    def run_file(filename)
      File.open(filename).each { |line| run_line(line) }
    end
  end
end