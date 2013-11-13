require 'rspec'
require_relative '../spec_helper'


module BillBot1

  describe "place toy robot" do

    let(:bot) { BillBot1::ToyRobot.new(BillBot1::Table.new(5)) }

    describe "#initialize" do
      it "initial status of toy robot" do
        bot.command("REPORT").should == nil
      end
    end

    describe "#place_valid" do

      it "place at 0,0,NORTH" do
           bot.command("PLACE 0,0,NORTH").should_not == nil
           bot.command("REPORT").should == "0,0,NORTH"
      end

      it "place at 0,0,EAST" do
        bot.command("PLACE 0,0,EAST").should_not == nil
        bot.command("REPORT").should == "0,0,EAST"
      end

      it "place at 2,2,EAST" do
        bot.command("PLACE 2,2,EAST").should_not == nil
        bot.command("REPORT").should == "2,2,EAST"
      end
    end

    describe "#place_invalid" do
      it "try to place at 0,0,UP" do
        bot.command("PLACE 0,0,UP").should == nil
        bot.command("REPORT").should == nil
      end

      it "try to place at 5,5,NORTH" do
        bot.command("PLACE 5,5,NORTH").should == nil
        bot.command("REPORT").should == nil
      end

      it "try to place at -1,0,NORTH" do
        bot.command("PLACE -1,,NORTH").should == nil
        bot.command("REPORT").should == nil
      end

    end

  end
end