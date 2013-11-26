package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.NotPlacedTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class TableRobotStopTest extends NotPlacedTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStopThenLeft() {
        assertTrue(mBot.place(4, 0, RobotHelper.EAST));
        // at east wall
        assertFalse(mBot.move());
        // at east wall
        assertFalse(mBot.move());
        // able to turn away
        assertTrue(mBot.left());
    }

    public void testStopThenRight() {
        assertTrue(mBot.place(4, 0, RobotHelper.EAST));
        // at east wall
        assertFalse(mBot.move());
        // at east wall
        assertFalse(mBot.move());
        // able to turn to south, also at wall
        assertTrue(mBot.right());
        // at south wall
        assertFalse(mBot.move());
    }

    public void testAtWallMove() {
        assertTrue(mBot.place(4, 0, RobotHelper.EAST));
        // at east wall
        assertFalse(mBot.move());
        // at east wall
        assertFalse(mBot.move());
    }

    public void testAtWallTurnMove() {
        assertTrue(mBot.place(4, 0, RobotHelper.EAST));
        // at east wall
        assertFalse(mBot.move());
        // at east wall
        assertFalse(mBot.move());
        // turn to north
        assertTrue(mBot.left());
        // able to move
        assertTrue(mBot.move());
    }

    public void testReport() {
        assertTrue(mBot.place(4, 0, RobotHelper.EAST));
        // at east wall
        assertFalse(mBot.move());
        // at east wall
        assertFalse(mBot.move());
        // able to report
        assertNotNull(mBot.report());
    }

    public void testPlace() {
        assertTrue(mBot.place(4, 0, RobotHelper.EAST));
        // at east wall
        assertFalse(mBot.move());
        // at east wall
        assertFalse(mBot.move());
        // able to be re-placed
        assertTrue(mBot.place(4, 0, RobotHelper.EAST));
    }

}
