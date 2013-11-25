package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class TableRobotTurnTest extends PlacedEastTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLeftReturn() {
		assertTrue(mBot.left());
	}

	public void testLeftCoordX() {
		assertTrue(mBot.left());
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_X, pos.getX());
	}

	public void testLeftCoordY() {
		assertTrue(mBot.left());
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_Y, pos.getY());
	}

	public void testLeftDir() {
		assertTrue(mBot.left());
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(RobotHelper.NORTH, pos.getDir());
	}

	public void testRightReturn() {
		assertTrue(mBot.right());
	}

	public void testRightCoordX() {
		assertTrue(mBot.right());
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_X, pos.getX());
	}

	public void testRightCoordY() {
		assertTrue(mBot.right());
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_Y, pos.getY());
	}

	public void testRightDir() {
		assertTrue(mBot.right());
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(RobotHelper.SOUTH, pos.getDir());
	}
}
