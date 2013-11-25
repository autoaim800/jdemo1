package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.NotPlacedTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * this test case tries to move robot beyond the boundary
 * 
 * @author bill
 * 
 */
public class TableRobotMoveHitWallTest extends NotPlacedTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHitEastWall() {
		assertTrue(mBot.place(INIT_Y, INIT_X, RobotHelper.EAST));
		for (int i = 0; i < INIT_WIDTH - 1; i++) {
			assertTrue(mBot.move());
		}
		assertFalse(mBot.move());
		assertFalse(mBot.move());
	}

	public void testHitEastWallTurnLeft() {
		assertTrue(mBot.place(INIT_Y, INIT_X, RobotHelper.EAST));
		for (int i = 0; i < INIT_WIDTH - 1; i++) {
			assertTrue(mBot.move());
		}
		// at wall
		assertFalse(mBot.move());
		assertFalse(mBot.move());
		// turn to north
		assertTrue(mBot.left());
	}

	public void testHitEastWallTurnLeftMove() {
		assertTrue(mBot.place(INIT_Y, INIT_X, RobotHelper.EAST));
		for (int i = 0; i < INIT_WIDTH - 1; i++) {
			assertTrue(mBot.move());
		}
		// at wall
		assertFalse(mBot.move());
		assertFalse(mBot.move());
		// turn to north
		assertTrue(mBot.left());
		// move to north
		assertTrue(mBot.move());
	}

	public void testHitEastWallTurnSouthWall() {
		assertTrue(mBot.place(INIT_Y, INIT_X, RobotHelper.EAST));
		for (int i = 0; i < INIT_WIDTH - 1; i++) {
			assertTrue(mBot.move());
		}
		// at east wall
		assertFalse(mBot.move());
		assertFalse(mBot.move());
		// turn to south, also at south wall
		assertTrue(mBot.right());
		// at south wall
		assertFalse(mBot.move());
		assertFalse(mBot.move());
	}

	public void testTurnHitWestWall() {
		assertTrue(mBot.place(INIT_Y, INIT_X, RobotHelper.EAST));
		// turn to north
		assertTrue(mBot.left());
		// turn to west
		assertTrue(mBot.left());
		// at west wall
		assertFalse(mBot.move());
		assertFalse(mBot.move());
	}

	// TODO a lot cases to hit three other walls

}
