package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.RobotHelper;

import junit.framework.TestCase;

public class SquareTableRangeTest extends TestCase {

    private SquareTable mTable;

    protected void setUp() throws Exception {
        super.setUp();
        mTable = new SquareTable(RobotHelper.DIR4, 5);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsInside() {
        assertTrue(mTable.isInside(1, 2));
    }

    public void testIsTable() {
        mTable = new SquareTable(RobotHelper.DIR4, 2);
        assertTrue(mTable.isTable());
    }

    public void testIsValidDirection() {
        mTable = new SquareTable(RobotHelper.DIR4, 2);
        assertTrue(mTable.isValidDirection(RobotHelper.DIR4[0]));
    }

    // TODO a lot of test cases of same approach
}
