package com.billsoft.jtoyrobot.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;

public class RobotConsoleCommandNegativeTest extends PlacedEastTestCase {

	private RobotConsole mCon;

	public RobotConsoleCommandNegativeTest() {
		mCon = new RobotConsole(mBot);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCommandPlace() {
		assertFalse(mCon.command("PLACE -1,-1,NORTH"));
	}

}
