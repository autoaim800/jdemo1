# BillBot1

Toy robot code test
The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units.
There are no other obstructions on the table surface.
The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement 
that would result in the robot falling from the table must be prevented, however further valid movement commands must still 
be allowed.
The origin (0,0) can be considered to be the SOUTH WEST most corner.
Toy robot supports command: PLACE, n,n,FACE, MOVE, LEFT, RIGHT, REPORT

## Make
   $ gem build BillBot1.gemspec
   
## Installation

Add this line to your application's Gemfile:

    gem 'BillBot1'

And then execute:

    $ bundle

Or install it yourself as:

    $ gem install BillBot1

## Usage
    test instruction: 

    # rspec
    $ rspec spec

    # cucumber
    $ cucumber features

    # runner
    $ ruby lib/runner.rb data/scr1.txt
    $ ruby lib/runner.rb < data/scr2.txt

    # unittest
    $ ruby -I"lib:test" test/toyrobot_cmd_negative_test.rb
    $ ruby -I"lib:test" test/toyrobot_move_negative_test.rb
    $ ruby -I"lib:test" test/toyrobot_move_test.rb
    $ ruby -I"lib:test" test/toyrobot_not_placed_test.rb
    $ ruby -I"lib:test" test/toyrobot_place_negative_test.rb
    $ ruby -I"lib:test" test/toyrobot_place_test.rb
    $ ruby -I"lib:test" test/toyrobot_report_test.rb
    $ ruby -I"lib:test" test/toyrobot_turn_test.rb

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request
