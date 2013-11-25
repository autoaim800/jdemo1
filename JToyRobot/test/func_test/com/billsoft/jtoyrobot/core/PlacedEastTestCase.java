package com.billsoft.jtoyrobot.core;

import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * generic test case that robot has been initialized and placed at 0,0 facing
 * east
 * 
 * @author bill
 * 
 */
public class PlacedEastTestCase extends NotPlacedTestCase {

    protected static final int INIT_Y = 0;
    protected static final int INIT_X = 0;

    protected void setUp() throws Exception {
        super.setUp();
        mBot.place(INIT_X, INIT_Y, RobotHelper.EAST);
    }
}
