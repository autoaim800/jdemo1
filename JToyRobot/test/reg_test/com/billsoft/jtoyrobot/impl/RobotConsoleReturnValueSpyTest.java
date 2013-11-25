package com.billsoft.jtoyrobot.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.RobotConsoleI;
import com.billsoft.jtoyrobot.core.RobotHelper;
import com.billsoft.jtoyrobot.core.SpyRobot;

/**
 * this class uses spy robot to compare responding value at console against real
 * robot, spy robot harness real robot
 * 
 * @author bill
 * 
 */
public class RobotConsoleReturnValueSpyTest extends TestCase {
	private SpyRobot mSpy;
	private RobotConsoleI mCon;

	public RobotConsoleReturnValueSpyTest() {
		mSpy = new SpyRobot(5);
		mCon = new RobotConsole(mSpy);
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
		boolean ret = mCon.command(RobotHelper.CMD_PLACE_00E);
		assertEquals(mSpy.isSuccess(), ret);
	}

	public void testCommandMove() {
		boolean ret = mCon.command(RobotHelper.CMD_MOVE);
		assertEquals(mSpy.isSuccess(), ret);
	}

	public void testCommandRight() {
		boolean ret = mCon.command(RobotHelper.CMD_RIGHT);
		assertEquals(mSpy.isSuccess(), ret);
	}

	public void testCommandLeft() {
		boolean ret = mCon.command(RobotHelper.CMD_LEFT);
		assertEquals(mSpy.isSuccess(), ret);
	}

	public void testCommandPlaceMove() {
		mCon.command(RobotHelper.CMD_PLACE_00E);
		boolean ret = mCon.command(RobotHelper.CMD_MOVE);
		assertEquals(mSpy.isSuccess(), ret);
	}

	public void testCommandPlaceReport() {
		assertTrue(mCon.command(RobotHelper.CMD_PLACE_00E));
		assertTrue(mCon.command(RobotHelper.CMD_REPORT));
		assertEquals(mSpy.isSuccess(), null != mCon.getLastKnownPosition());
	}

}
