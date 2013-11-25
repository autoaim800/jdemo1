package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.NotPlacedTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class TableRobotNotPlacedTest extends NotPlacedTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetTable() {
		assertNotNull(mBot.getTable());
	}

	public void testTableRobot() {
		assertNotNull(mBot);
	}

	public void testLeft() {
		assertFalse(mBot.left());
	}

	public void testRight() {
		assertFalse(mBot.right());
	}

	public void testMove() {
		assertFalse(mBot.move());
	}

	public void testReport() {
		assertNull(mBot.report());
	}

	public void testPlace() {
		assertTrue(mBot.place(0, 0, RobotHelper.EAST));
	}

}
