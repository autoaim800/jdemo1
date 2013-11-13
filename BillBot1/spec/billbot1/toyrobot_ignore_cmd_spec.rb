require 'rspec'
require_relative '../spec_helper'


module BillBot1

  describe "ignore command toy robot" do

    let(:bot) { BillBot1::ToyRobot.new(BillBot1::Table.new(5)) }

    describe "#prior_to_place" do
      it "ignore move" do
        bot.command("MOVE").should == nil
        bot.command("MOVE").should == nil
      end

      it "ignore turn left" do
        bot.command("LEFT").should == nil
      end

      it "ignore turn right" do
        bot.command("RIGHT").should == nil
      end

      it "ignore turn right and left" do
        bot.command("RIGHT").should == nil
        bot.command("LEFT").should == nil
        bot.command("RIGHT").should == nil
      end

      it "ignore unknown command" do
        bot.command("DANCE").should == nil
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