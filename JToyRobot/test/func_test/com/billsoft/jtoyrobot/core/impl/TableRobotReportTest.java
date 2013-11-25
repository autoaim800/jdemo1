package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class TableRobotReportTest extends PlacedEastTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReportReturn() {
        assertNotNull(mBot.report());
    }

    /**
     * verify coord-x of reported position
     */
    public void testReportCoordX() {
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(INIT_X, pos.getX());
    }

    /**
     * verify coord-y of reported position
     */
    public void testReportCoordY() {
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(INIT_Y, pos.getY());
    }

    /**
     * verify facing direction of reported position
     */
    public void testReportDir() {
        Position pos = mBot.report();
        assertNotNull(pos);
        assertEquals(RobotHelper.EAST, pos.getDir());
    }
}
