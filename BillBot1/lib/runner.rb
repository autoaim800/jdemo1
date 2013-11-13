require_relative 'BillBot1'

# cli entry here
runner = BillBot1::ToyRobotRunner.new()
if ARGV.length > 0
  # run file mode
  runner.run_file(ARGV[0])
else
  # run from stdin mode
  command = STDIN.gets
  while command
    runner.run_line(command)
    command = STDIN.gets
  end
end