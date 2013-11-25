package com.billsoft.jtoyrobot.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.RobotConsoleI;
import com.billsoft.jtoyrobot.core.RobotHelper;
import com.billsoft.jtoyrobot.core.RobotI;
import com.billsoft.jtoyrobot.core.SpyRobot;

public class RobotConsoleReturnValueSpyTest extends TestCase {
    private SpyRobot mSpy;
    private RobotConsoleI mCon;

    public RobotConsoleReturnValueSpyTest() {
        mSpy = new SpyRobot(5);
        mCon = new RobotConsole(mSpy);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCommandPlace() {
        boolean ret = mCon.command(RobotHelper.CMD_PLACE_00E);
        assertEquals(mSpy.isSuccess(), ret);
    }

    public void testCommandMove() {
        boolean ret = mCon.command(RobotHelper.CMD_MOVE);
        assertEquals(mSpy.isSuccess(), ret);
    }
    
    public void testCommandRight() {
        boolean ret = mCon.command(RobotHelper.CMD_RIGHT);
        assertEquals(mSpy.isSuccess(), ret);
    }
    
    public void testCommandLeft() {
        boolean ret = mCon.command(RobotHelper.CMD_LEFT);
        assertEquals(mSpy.isSuccess(), ret);
    }

    public void testCommandPlaceMove() {
        mCon.command(RobotHelper.CMD_PLACE_00E);
        boolean ret = mCon.command(RobotHelper.CMD_MOVE);
        assertEquals(mSpy.isSuccess(), ret);
    }

    public void testCommandPlaceReport() {
        mCon.command(RobotHelper.CMD_PLACE_00E);
        boolean ret = mCon.command(RobotHelper.CMD_REPORT);
        assertEquals(mSpy.isSuccess(), null != mSpy.getPosition());
    }

}
