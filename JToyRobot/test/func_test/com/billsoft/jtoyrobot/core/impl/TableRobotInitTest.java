package com.billsoft.jtoyrobot.core.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.RobotHelper;

public class TableRobotInitTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetTableWidth() {
        TableRobot bot = new TableRobot(5);
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertEquals("table width mismatch", table.getWidth(), bot.getTable()
                .getWidth());
    }

    public void testGetTableDirs() {
        TableRobot bot = new TableRobot(5);
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertEquals("faces mismatch", table.getDirs().length, bot.getTable()
                .getDirs().length);
        for (int i = 0; i < table.getDirs().length; i++) {
            assertEquals("face value mismatch", table.getDirs()[i], bot
                    .getTable().getDirs()[i]);
        }
    }

    /**
     * test constructor with parameter: integer width
     */
    public void testTableRobotInt() {
        TableRobot bot = new TableRobot(5);
        assertNotNull(bot.getTable());
    }

    /**
     * test constructor with parameter: surface2dI
     */
    public void testTableRobotSurface2DI() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        TableRobot bot = new TableRobot(table);
        assertNotNull(bot.getTable());
    }

}
