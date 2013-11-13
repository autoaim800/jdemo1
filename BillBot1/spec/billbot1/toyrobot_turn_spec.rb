require 'rspec'
require_relative '../spec_helper'


module BillBot1

  describe "turn toy robot" do

    let(:bot) { BillBot1::ToyRobot.new(BillBot1::Table.new(5)) }

    describe "#turn_prior_to_place" do
      it "turn left" do
        bot.command("LEFT").should == nil
        bot.command("REPORT").should == nil
      end

      it "turn right" do
        bot.command("RIGHT").should == nil
        bot.command("REPORT").should == nil
      end

      it "turn rightx2" do
        bot.command("RIGHT").should == nil
        bot.command("RIGHT").should == nil
        bot.command("REPORT").should == nil
      end
    end

    describe "#turn_right" do
      it "turn right at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("REPORT").should == "0,0,EAST"
      end

      it "turn rightx2 at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("REPORT").should == "0,0,SOUTH"
      end

      it "turn rightx4 at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("RIGHT").should_not == nil
        bot.command("REPORT").should == "0,0,NORTH"
      end
    end

    describe "#turn_left" do
      it "turn left at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("REPORT").should == "0,0,WEST"
      end

      it "turn leftx2 at 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("LEFT").should_not == nil
        bot.command("REPORT").should == "0,0,SOUTH"
      end

      it "turn leftx4 at 0,0,NORTH" do
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