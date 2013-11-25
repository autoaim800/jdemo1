package com.billsoft.jtoyrobot.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.MockRobot;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class RobotConsolePerfMockTest extends TestCase {

    private RobotConsole mCon;
    private MockRobot mMock;

    public RobotConsolePerfMockTest() {
        mMock = new MockRobot(5);
        mCon = new RobotConsole(mMock);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCommandMove() {
        // bot has not been placed, should return false
        assertFalse(mCon.command(RobotHelper.CMD_MOVE));
    }

    public void testCommandPlaceMove() {
        assertTrue(mCon.command(RobotHelper.CMD_PLACE_00E));
        assertTrue(mCon.command(RobotHelper.CMD_MOVE));
    }

}
