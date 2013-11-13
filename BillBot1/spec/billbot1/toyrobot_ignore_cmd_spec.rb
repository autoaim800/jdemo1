require 'rspec'
require_relative '../spec_helper'


module BillBot1

  describe "ignore command toy robot" do

    let(:bot) { BillBot1::ToyRobot.new(BillBot1::Table.new(5)) }

    describe "#move_prior_to_place" do
      it "not placed" do
        bot.command("JUMP").should == nil
      end
    end

    describe "#unknown_cmd" do
      it "place at 0,0,NORTH then jump" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("JUMP").should == nil
      end

      it "place at 0,0,NORTH then dance" do
        bot.command("PLACE 0,0,NORTH").should_not == nil
        bot.command("DACE").should == nil
      end
    end

    describe "#invalid_format" do
      it "try to place at 0 0 WEST" do
        bot.command("PLACE 0 0 WEST").should == nil
      end

      it "try to place at 0,0 WEST" do
        bot.command("PLACE 0,0 WEST").should == nil
      end
    end

  end
end