package com.billsoft.jtoyrobot.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.MockRobot;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * test command deliver ability of console using a mock robot
 * 
 * @author bill
 * 
 */
public class RobotConsolePerfMockTest extends TestCase {

    private RobotConsole mCon5;
    private RobotConsole mCon6;
    private MockRobot mMock5;
    private MockRobot mMock6;

    public RobotConsolePerfMockTest() {
        mMock5 = new MockRobot(5);
        mMock6 = new MockRobot(6);
        mCon5 = new RobotConsole(mMock5);
        mCon6 = new RobotConsole(mMock6);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCommandMove() {
        // bot has not been placed, should return false
        assertFalse(mCon5.command(RobotHelper.CMD_MOVE));

        assertTrue(mCon5.command(RobotHelper.CMD_PLACE_00E));
        assertTrue(mCon5.command(RobotHelper.CMD_MOVE));
    }

    public void testCommandPlaceMoveSeq() {
        assertTrue(mCon5.command(RobotHelper.CMD_PLACE_00E));
        assertTrue(mCon5.command(RobotHelper.CMD_MOVE));

        assertTrue(mCon6.command(RobotHelper.CMD_PLACE_00E));
        assertTrue(mCon6.command(RobotHelper.CMD_MOVE));
    }

    public void testCommandPlaceMoveSim() {
        Thread t1 = new Thread() {
            public void run() {
                assertTrue(mCon5.command(RobotHelper.CMD_PLACE_00E));
                assertTrue(mCon5.command(RobotHelper.CMD_MOVE));
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                assertTrue(mCon6.command(RobotHelper.CMD_PLACE_00E));
                assertTrue(mCon6.command(RobotHelper.CMD_MOVE));
            }
        };

        t1.start();
        t2.start();

    }

}
