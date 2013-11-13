Given(/^a table width (\d+)$/) do |arg1|
  @table = BillBot1::Table.new(arg1.to_i)
  @bot = BillBot1::ToyRobot.new(@table)
end

When(/^place at "(.*?)"$/) do |arg1|
  @bot.command("PLACE #{arg1}").should_not == nil
end

When(/^try to place at "(.*?)"$/) do |arg1|
  @bot.command("PLACE #{arg1}").should == nil
end

Then(/^report as "(.*?)"$/) do |arg1|
  @bot.command("REPORT").should == arg1
end

When(/^try to move$/) do
  @bot.command("MOVE").should == nil
end

When(/^manage to move$/) do
  @bot.command("MOVE").should_not == nil
end

When(/^turn right$/) do
  @bot.command("RIGHT")
end

When(/^turn left$/) do
  @bot.command("LEFT")
end

When(/^try to turn right$/) do
  @bot.command("RIGHT").should == nil
end

When(/^try to turn left$/) do
  @bot.command("LEFT").should == nil
end

When(/^try to report$/) do
  @bot.command("REPORT").should == nil
end

Then(/^manage to report$/) do
  @bot.command("REPORT").should_not == nil
end

When(/^try to dance$/) do
  @bot.command("DANCE").should == nil
end

Then(/^try to send "(.*?)"$/) do |arg1|
  @bot.command(arg1).should == nil
end