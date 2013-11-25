package com.billsoft.jtoyrobot.core.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.RobotHelper;

public class TableRobotInitTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetTableWidth() {
        TableRobot bot = new TableRobot(5);
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertEquals("table width mismatch", table.getWidth(), bot.getTable().getWidth());
    }

    public void testGetTableDirs() {
        TableRobot bot = new TableRobot(5);
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertEquals("faces mismatch", table.getDirs().length, bot.getTable().getDirs().length);
        for (int i = 0; i < table.getDirs().length; i++) {
            assertEquals("face value mismatch", table.getDirs()[i], bot.getTable().getDirs()[i]);
        }
    }

    public void testTableRobot() {
        TableRobot bot = new TableRobot(5);
        assertNotNull(bot.getTable());
    }

}
