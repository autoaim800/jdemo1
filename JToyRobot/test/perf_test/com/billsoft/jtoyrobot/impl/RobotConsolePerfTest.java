package com.billsoft.jtoyrobot.impl;

import com.billsoft.jtoyrobot.core.FakeRobot;
import com.billsoft.jtoyrobot.core.PerfTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * this class test console commanding performance
 * 
 * @author bill
 * 
 */
public class RobotConsolePerfTest extends PerfTestCase {

	private FakeRobot mBot;
	private RobotConsole mCon;

	public RobotConsolePerfTest() {
		mBot = new FakeRobot();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		assertNotNull(mBot);
		mCon = new RobotConsole(mBot);
		assertNotNull(mCon);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCommandMove() {
		mCon.command(RobotHelper.CMD_PLACE_00E);
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < LOOP_ROUND; i++) {
			mCon.command(RobotHelper.CMD_MOVE);
		}
		long t2 = System.currentTimeMillis();
		assertTrue(t2 - t1 < 1000);
	}

	public void testCommandPlace() {
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < LOOP_ROUND; i++) {
			mCon.command(RobotHelper.CMD_PLACE_00E);
		}
		long t2 = System.currentTimeMillis();
		assertTrue(t2 - t1 < 1000);
	}

	// TODO a lot of commands and combination of commands test case

}
