package com.billsoft.jtoyrobot.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.FakeRobot;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class RobotConsoleSendCmdFakeTest extends TestCase {

    private RobotConsole mCon;
    private FakeRobot mFake;

    public RobotConsoleSendCmdFakeTest() {
        mFake = new FakeRobot();
        mCon = new RobotConsole(mFake);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCommandMove() {
        mCon.command(RobotHelper.CMD_MOVE);
        assertTrue(RobotHelper.CMD_MOVE.equals(mFake.getLastCommand()));
    }
    
    public void testCommandLeft() {
        mCon.command(RobotHelper.CMD_LEFT);
        assertTrue(RobotHelper.CMD_LEFT.equals(mFake.getLastCommand()));
    }
    
    public void testCommandRight() {
        mCon.command(RobotHelper.CMD_RIGHT);
        assertTrue(RobotHelper.CMD_RIGHT.equals(mFake.getLastCommand()));
    }
    
    public void testCommandPlace() {
        mCon.command(RobotHelper.CMD_PLACE_00E);
        assertTrue(RobotHelper.CMD_PLACE_00E.equals(mFake.getLastCommand()));
    }

}
