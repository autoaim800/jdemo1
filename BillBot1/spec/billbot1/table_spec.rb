require 'rspec'
require_relative '../spec_helper'

describe '#table_width' do

  it 'should return correct table width 5' do
    @table = BillBot1::Table.new(5)
    @table.width.should == 5
  end

  it 'should return correct table width 6' do
    @table = BillBot1::Table.new(6)
    @table.width.should == 6
  end
end