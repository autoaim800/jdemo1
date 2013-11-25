package com.billsoft.jtoyrobot.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * verify command can be applied to robot
 * 
 * @author bill
 * 
 */
public class RobotConsolePlacedTest extends PlacedEastTestCase {

    private RobotConsole mCon;

    protected void setUp() throws Exception {
        super.setUp();
        mCon = new RobotConsole(mBot);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCommandLeftPos() {
        assertTrue(mCon.command(RobotHelper.CMD_LEFT));
        assertNotNull(mBot.report());
    }

    public void testCommandLeftDir() {
        assertTrue(mCon.command(RobotHelper.CMD_LEFT));
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(RobotHelper.NORTH, pos.getDir());
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

    public void testCommandRightPos() {
        assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
        assertNotNull(mBot.report());
    }

    public void testCommandRightDir() {
        assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(RobotHelper.SOUTH, pos.getDir());
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

    public void testCommandNonePos() {
        assertNotNull(mBot.report());
    }

    public void testCommandNoneDir() {
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(RobotHelper.EAST, pos.getDir());
    }

    public void testCommandNoneCoordX() {
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(INIT_X, pos.getX());
    }

    public void testCommandNoneCoordY() {
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(INIT_Y, pos.getY());
    }

    public void testCommandMovePos() {
        assertTrue(mCon.command(RobotHelper.CMD_MOVE));
        assertNotNull(mBot.report());
    }

    public void testCommandMoveDir() {
        assertTrue(mCon.command(RobotHelper.CMD_MOVE));
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(RobotHelper.EAST, pos.getDir());
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

}
