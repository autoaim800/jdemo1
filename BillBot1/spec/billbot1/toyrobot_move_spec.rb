require 'rspec'
require_relative '../spec_helper'


module BillBot1

  describe "move toy robot" do

    let(:bot) { BillBot1::ToyRobot.new(BillBot1::Table.new(5)) }

    describe "#move_prior_to_place" do
      it "not placed" do
        bot.command("MOVE").should == nil
      end
    end

    describe "#move_valid" do
      it "place at 0,0,NORTH" do
           bot.command("PLACE 0,0,NORTH").should_not == nil
           bot.command("MOVE").should_not == nil
           bot.command("REPORT").should == "0,1,NORTH"
      end

      it "move to boundary from 0,0,NORTH" do
        bot.command("PLACE 0,0,NORTH").should == "placed"
        bot.command("REPORT").should == "0,0,NORTH"
        bot.command("MOVE").should == 3
        bot.command("REPORT").should == "0,1,NORTH"
        bot.command("MOVE").should == 2
        bot.command("REPORT").should == "0,2,NORTH"
        bot.command("MOVE").should == 1
        bot.command("REPORT").should == "0,3,NORTH"
        bot.command("MOVE").should == 0
        bot.command("REPORT").should == "0,4,NORTH"
        bot.command("MOVE").should == nil
        bot.command("REPORT").should == "0,4,NORTH"
        bot.command("MOVE").should == nil
        bot.command("REPORT").should == "0,4,NORTH"
      end

      it "move to boundary from 2,2,NORTH" do
        bot.command("PLACE 2,2,NORTH").should == "placed"
        bot.command("REPORT").should == "2,2,NORTH"
        bot.command("MOVE").should == 1
        bot.command("REPORT").should == "2,3,NORTH"
        bot.command("MOVE").should == 0
        bot.command("REPORT").should == "2,4,NORTH"
        bot.command("MOVE").should == nil
        bot.command("REPORT").should == "2,4,NORTH"
        bot.command("MOVE").should == nil
        bot.command("REPORT").should == "2,4,NORTH"
        bot.command("MOVE").should == nil
        bot.command("REPORT").should == "2,4,NORTH"
      end
    end

    describe "#move_at_boundary" do

      it "ignore to move when place at 0,0,WEST" do
        bot.command("PLACE 0,0,WEST").should_not == nil
        bot.command("MOVE").should == nil
      end

      it "stationary when place at 0,0,WEST and move" do
        bot.command("PLACE 0,0,WEST").should_not == nil
        bot.command("MOVE").should == nil
        bot.command("REPORT").should == "0,0,WEST"
      end

      it "accept turn away from boundary" do
        bot.command("PLACE 0,0,WEST").should_not == nil
        bot.command("MOVE").should == nil
        bot.command("REPORT").should == "0,0,WEST"
        bot.command("RIGHT").should_not == nil
        bot.command("REPORT").should == "0,0,NORTH"
      end
    end

  end
end