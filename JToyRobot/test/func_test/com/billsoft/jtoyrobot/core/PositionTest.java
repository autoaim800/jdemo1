package com.billsoft.jtoyrobot.core;

import junit.framework.TestCase;

public class PositionTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPosition() {
        assertNotNull(new Position(0, 0, RobotHelper.EAST));
    }

    public void testGetAxisX() {
        Position pos = new Position(0, 1, RobotHelper.EAST);
        assertEquals(0, pos.getX());
    }

    public void testGetAxisY() {
        Position pos = new Position(0, 1, RobotHelper.EAST);
        assertEquals(1, pos.getY());
    }

    public void testGetDirection() {
        Position pos = new Position(0, 1, RobotHelper.NORTH);
        assertEquals(RobotHelper.NORTH, pos.getDir());
    }

    public void testToString() {
        Position pos = new Position(0, 1, RobotHelper.NORTH);
        assertEquals("0,1,NORTH", pos.toString());

    }

}
