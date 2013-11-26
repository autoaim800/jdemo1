package com.billsoft.jtoyrobot.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class RobotConsoleRandCmdTest extends PlacedEastTestCase {
    private RobotConsole mCon;
    private static final int POOL_SIZE = 10;
    private static final String[] POOL = new String[] { RobotHelper.CMD_LEFT,
            RobotHelper.CMD_MOVE, RobotHelper.CMD_PLACE_00E,
            RobotHelper.CMD_REPORT, RobotHelper.CMD_RIGHT,
            RobotHelper.CMD_LEFT, RobotHelper.CMD_MOVE,
            RobotHelper.CMD_PLACE_44W, RobotHelper.CMD_REPORT,
            RobotHelper.CMD_RIGHT };

    public RobotConsoleRandCmdTest() {
        mCon = new RobotConsole(mBot);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRandCommand() {
        for (int i = 0; i < RobotHelper.REPEAT; i++) {
            int index = (int) (Math.random() * POOL_SIZE);
            String cmd = POOL[index];
            if (RobotHelper.CMD_MOVE.equals(cmd)) {
                // move command may be ignored
                mCon.command(cmd);
            } else {
                assertTrue(mCon.command(cmd));
            }
        }
    }

    // TODO a lot of other combination of random commands test cases
}
