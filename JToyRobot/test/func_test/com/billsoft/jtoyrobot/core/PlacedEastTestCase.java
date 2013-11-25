package com.billsoft.jtoyrobot.core;

/**
 * generic test case that robot has been initialized and placed at 0,0 facing
 * east
 * 
 * @author bill
 * 
 */
public class PlacedEastTestCase extends NotPlacedTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mBot.place(INIT_X, INIT_Y, RobotHelper.EAST);
	}
}
