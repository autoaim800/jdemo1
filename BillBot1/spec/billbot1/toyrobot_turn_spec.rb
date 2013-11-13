require 'rspec'
require_relative '../spec_helper'


module BillBot1

  describe "turn toy robot" do

    let(:bot) { BillBot1::ToyRobot.new(BillBot1::Table.new(5)) }

    describe "#turn_prior_to_place" do

      it "should not turn left" do
        bot.command("LEFT").should == nil
        bot.command("REPORT").should == nil
      end

      it "should not turn right" do
        bot.command("RIGHT").should == nil
        bot.command("REPORT").should == nil
      end

      it "should not turn right x2" do
        bot.command("RIGHT").should == nil
        bot.command("RIGHT").should == nil
        bot.command("REPORT").should == nil
      end
    end

    describe "#turn_right" do

      it "should turn right at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("REPORT").should == "0,0,EAST"
      end

      it "should turn right x2 at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("REPORT").should == "0,0,SOUTH"
      end

      it "should turn right x4 at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("REPORT").should == "0,0,NORTH"
      end
    end

    describe "#turn_left" do
      it "should turn left at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("REPORT").should == "0,0,WEST"
      end

      it "should turn left at 2,2,EAST" do
        bot.command("PLACE 2,2,EAST").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("REPORT").should == "2,2,NORTH"
      end

      it "should turn leftx2 at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("REPORT").should == "0,0,SOUTH"
      end

      it "should turn leftx4 at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("REPORT").should == "0,0,NORTH"
      end
    end
  end
end