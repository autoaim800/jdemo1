package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.NotPlacedTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * this class verifies whether the robot can be placed once or twice at ordinary
 * places 00E 22N
 * 
 * @author bill
 * 
 */
public class TableRobotPlaceTest extends NotPlacedTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPlace00E() {
		assertTrue(mBot.place(0, 0, RobotHelper.EAST));
	}

	public void testPlace00U() {
		assertFalse(mBot.place(0, 0, RobotHelper.UP));
	}

	public void testPlace00E22N() {
		assertTrue(mBot.place(0, 0, RobotHelper.EAST));
		assertTrue(mBot.place(2, 2, RobotHelper.NORTH));
	}

}
