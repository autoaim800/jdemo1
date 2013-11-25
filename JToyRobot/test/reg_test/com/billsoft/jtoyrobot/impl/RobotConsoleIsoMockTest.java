package com.billsoft.jtoyrobot.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.MockRobot;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * this class tests robot console function with mock robot
 * 
 * @author bill
 * 
 */
public class RobotConsoleIsoMockTest extends TestCase {

	private RobotConsole mCon;
	private MockRobot mMock;

	public RobotConsoleIsoMockTest() {
		mMock = new MockRobot(5);
		mCon = new RobotConsole(mMock);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCommandMove() {
		// bot has not been placed, should return false
		assertFalse(mCon.command(RobotHelper.CMD_MOVE));
	}

	public void testCommandPlaceMove() {
		assertTrue(mCon.command(RobotHelper.CMD_PLACE_00E));
		assertTrue(mCon.command(RobotHelper.CMD_MOVE));
	}

	public void testGetLastKnownPositionNoReport() {
		// must be null prior cmd:report
		assertNull(mCon.getLastKnownPosition());
	}

	public void testGetLastKnownPositionReported() {
		assertTrue(mCon.command(RobotHelper.CMD_PLACE_00E));
		assertNull(mCon.getLastKnownPosition());
		assertTrue(mCon.command(RobotHelper.CMD_REPORT));
		assertNotNull(mCon.getLastKnownPosition());
	}
}
