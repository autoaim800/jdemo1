require 'rspec'
require_relative '../spec_helper'


module BillBot1

  describe "place toy robot" do

    let(:bot) { BillBot1::ToyRobot.new(BillBot1::Table.new(5)) }

    describe "#initialize" do
      it "should ignore report" do
        bot.command("REPORT").should == nil
      end
    end

    describe "#place_valid" do

      it "should be place at 0,0,NORTH" do
           bot.command("PLACE 0,0,NORTH").should_not == nil
           bot.command("REPORT").should == "0,0,NORTH"
      end

      it "should be place at 0,0,EAST" do
        bot.command("PLACE 0,0,EAST").should_not == nil
        bot.command("REPORT").should == "0,0,EAST"
      end

      it "should be place at 2,2,EAST" do
        bot.command("PLACE 2,2,EAST").should_not == nil
        bot.command("REPORT").should == "2,2,EAST"
      end
    end

    describe "#place_invalid" do
      it "should not be place at 0,0,UP" do
        bot.command("PLACE 0,0,UP").should == nil
        bot.command("REPORT").should == nil
      end

      it "should not be place at 5,5,NORTH" do
        bot.command("PLACE 5,5,NORTH").should == nil
        bot.command("REPORT").should == nil
      end

      it "should not be place at -1,0,NORTH" do
        bot.command("PLACE -1,,NORTH").should == nil
        bot.command("REPORT").should == nil
      end

      it "should not be place at 0,-1,NORTH" do
        bot.command("PLACE -1,,NORTH").should == nil
        bot.command("REPORT").should == nil
      end

    end

  end
end