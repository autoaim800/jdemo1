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
      end
    end

    describe "#move_at_boundary" do
      it "try to place at 0,0,WEST" do
        bot.command("PLACE 0,0,WEST").should_not == nil
        bot.command("MOVE").should == nil
      end
    end

  end
end