package com.billsoft.jtoyrobot.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class RobotConsoleRepeatCmdTest extends PlacedEastTestCase {

    private RobotConsole mCon;
    

    public RobotConsoleRepeatCmdTest() {
        super();
        mCon = new RobotConsole(mBot);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRepeatMove() {
        for (int i = 0; i < RobotHelper.REPEAT; i++) {
            assertTrue(mCon.command(RobotHelper.CMD_MOVE));
        }
    }

    public void testRepeatLeft() {
        for (int i = 0; i < RobotHelper.REPEAT; i++) {
            assertTrue(mCon.command(RobotHelper.CMD_LEFT));
        }
    }

    // TODO a lot of other repeat test cases

}
