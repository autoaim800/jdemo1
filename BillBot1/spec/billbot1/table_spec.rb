require 'rspec'
require_relative '../spec_helper'

describe '#table' do

  it 'table width 5' do
    @table = BillBot1::Table.new(5)
    @table.width.should == 5
  end

  it 'table width 6' do
    @table = BillBot1::Table.new(6)
    @table.width.should == 6
  end
end