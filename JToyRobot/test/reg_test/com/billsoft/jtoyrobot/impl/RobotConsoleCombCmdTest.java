package com.billsoft.jtoyrobot.impl;

import com.billsoft.jtoyrobot.core.PlacedEastTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

public class RobotConsoleCombCmdTest extends PlacedEastTestCase {

    private RobotConsole mCon;

    public RobotConsoleCombCmdTest() {
        super();
        mCon = new RobotConsole(mBot);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCombPlaceMove() {
        assertTrue(mCon.command(RobotHelper.CMD_PLACE_00E));
        assertTrue(mCon.command(RobotHelper.CMD_MOVE));
    }

    /**
     * combination of 2 commands
     */
    public void testCombTurnMove() {
        assertTrue(mCon.command(RobotHelper.CMD_LEFT));
        assertTrue(mCon.command(RobotHelper.CMD_MOVE));
        assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
    }

    /**
     * combination of 3 commands
     */
    public void testCombMoveTurnReport() {
        assertTrue(mCon.command(RobotHelper.CMD_REPORT));
        assertTrue(mCon.command(RobotHelper.CMD_MOVE));

        assertTrue(mCon.command(RobotHelper.CMD_RIGHT));
        assertTrue(mCon.command(RobotHelper.CMD_REPORT));
    }
    
    public void testRepeatLeftMove() {
        for (int i = 0; i < RobotHelper.REPEAT; i++) {
            assertTrue(mCon.command(RobotHelper.CMD_MOVE));
            assertTrue(mCon.command(RobotHelper.CMD_LEFT));
        }
    }

}
