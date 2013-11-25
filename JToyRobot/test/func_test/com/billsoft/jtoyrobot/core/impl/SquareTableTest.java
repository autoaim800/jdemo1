package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.RobotHelper;

import junit.framework.TestCase;

public class SquareTableTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * test the default constructor with 4-dir and width of 5
     */
    public void testSquareTableImpl() {
        // initialize table with 4-direction and width:5
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertNotNull(table);
        assertTrue(table.isTable());
    }

    /**
     * test square table's width and length
     */
    public void testGetWidthLength() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertEquals(table.getLength(), table.getWidth());
    }

    /**
     * test square table's width of 5
     */
    public void testGetWidth5() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertEquals("table width mismatch", 5, table.getWidth());
    }

    /**
     * test square table's width of 6
     */
    public void testGetWidth6() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 6);
        assertEquals("table width mismatch", 6, table.getWidth());
    }

    /**
     * test the 4-dir of table
     */
    public void testGetDirs4() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);

        int[] tableDirs = table.getDirs();

        assertEquals("dir length does not match", RobotHelper.DIR4.length, tableDirs.length);

        for (int i = 0; i < tableDirs.length; i++) {
            assertEquals("dir value does not match", RobotHelper.DIR4[i], tableDirs[i]);
        }
    }

    /**
     * test the 6-dir of table
     */
    public void testGetDirs6() {
        SquareTable table = new SquareTable(RobotHelper.DIR6, 5);

        int[] tableDirs = table.getDirs();

        assertEquals("dir length does not match", RobotHelper.DIR6.length, tableDirs.length);

        for (int i = 0; i < tableDirs.length; i++) {
            assertEquals("dir value does not match", RobotHelper.DIR6[i], tableDirs[i]);
        }
    }

    public void testIsInside() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertTrue(table.isInside(1, 2));
    }

    public void testIsTable() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertTrue(table.isTable());
    }

    public void testIsValidDirection() {
        SquareTable table = new SquareTable(RobotHelper.DIR4, 5);
        assertTrue(table.isValidDirection(RobotHelper.DIR4[0]));
    }

}
