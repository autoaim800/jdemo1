package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class TableRobotMoveTest extends PlacedEastTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testMoveReturn() {
        assertTrue(mBot.move());
    }

    public void testMovedCoordX() {
        assertTrue(mBot.move());
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(INIT_X + 1, pos.getX());
    }

    public void testMovedCoordY() {
        assertTrue(mBot.move());
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(INIT_Y, pos.getY());
    }

    public void testMovedDir() {
        assertTrue(mBot.move());
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(RobotHelper.EAST, pos.getDir());
    }

}
