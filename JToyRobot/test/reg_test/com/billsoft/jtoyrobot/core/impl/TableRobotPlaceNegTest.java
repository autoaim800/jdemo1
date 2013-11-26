package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.NotPlacedTestCase;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * negative test cases for trying to place robot at invalid coordinate
 * 
 * @author bill
 * 
 */
public class TableRobotPlaceNegTest extends NotPlacedTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPlace00U() {
        assertFalse(mBot.place(0, 0, RobotHelper.UP));
    }

    public void testPlace0_1E() {
        assertFalse(mBot.place(0, -1, RobotHelper.EAST));
    }

    public void testPlace_10E() {
        assertFalse(mBot.place(-1, 0, RobotHelper.EAST));
    }

    public void testPlace_11E() {
        assertFalse(mBot.place(-1, -1, RobotHelper.EAST));
    }

    // TODO omit a lot of invalid combination of coordinate/direction test cases

    public void testPlace00E() {
        assertTrue(mBot.place(0, 0, RobotHelper.EAST));
        Position pos1 = mBot.report();
        assertNotNull(pos1);

        assertFalse(mBot.place(0, 0, RobotHelper.UP));

        Position pos2 = mBot.report();
        assertNull(pos2);
    }

}
