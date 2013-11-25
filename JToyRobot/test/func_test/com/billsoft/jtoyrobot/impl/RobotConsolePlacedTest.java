package com.billsoft.jtoyrobot.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * verify commands can be applied to robot when robot is placed
 * 
 * @author bill
 * 
 */
public class RobotConsolePlacedTest extends PlacedEastTestCase {

	private RobotConsole mCon;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mCon = new RobotConsole(mBot);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCommandLeftCoordX() {
		assertTrue(mCon.command(RobotHelper.CMD_LEFT));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_X, pos.getX());
	}

	public void testCommandLeftCoordY() {
		assertTrue(mCon.command(RobotHelper.CMD_LEFT));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_Y, pos.getY());
	}

	public void testCommandLeftDir() {
		assertTrue(mCon.command(RobotHelper.CMD_LEFT));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(RobotHelper.NORTH, pos.getDir());
	}

	public void testCommandLeftPos() {
		assertTrue(mCon.command(RobotHelper.CMD_LEFT));
		assertNotNull(mBot.report());
	}

	public void testCommandMoveCoordX() {
		assertTrue(mCon.command(RobotHelper.CMD_MOVE));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_X + 1, pos.getX());
	}

	public void testCommandMoveCoordY() {
		assertTrue(mCon.command(RobotHelper.CMD_MOVE));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_Y, pos.getY());
	}

	public void testCommandMoveDir() {
		assertTrue(mCon.command(RobotHelper.CMD_MOVE));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(RobotHelper.EAST, pos.getDir());
	}

	public void testCommandMovePos() {
		assertTrue(mCon.command(RobotHelper.CMD_MOVE));
		assertNotNull(mBot.report());
	}

	public void testCommandReportAfterReport() {
		assertNull(mCon.getLastKnownPosition());
		assertTrue(mCon.command(RobotHelper.CMD_REPORT));
		assertNotNull(mCon.getLastKnownPosition());
	}

	public void testCommandReportPriorReport() {
		assertNull(mCon.getLastKnownPosition());
	}

	public void testCommandRightCoordX() {
		assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_X, pos.getX());
	}

	public void testCommandRightCoordY() {
		assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_Y, pos.getY());
	}

	public void testCommandRightDir() {
		assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(RobotHelper.SOUTH, pos.getDir());
	}

	public void testCommandRightPos() {
		assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
		assertNotNull(mBot.report());
	}

	public void testCommandStationaryCoordX() {
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_X, pos.getX());
	}

	public void testCommandStationaryCoordY() {
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(INIT_Y, pos.getY());
	}

	public void testCommandStationaryDir() {
		Position pos = mBot.report();
		assertNotNull(pos);
		assertEquals(RobotHelper.EAST, pos.getDir());
	}

	public void testCommandStationaryPos() {
		assertNotNull(mBot.report());
	}

}
