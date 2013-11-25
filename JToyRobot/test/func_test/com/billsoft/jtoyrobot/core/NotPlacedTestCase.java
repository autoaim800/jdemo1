package com.billsoft.jtoyrobot.core;

import com.billsoft.jtoyrobot.core.impl.TableRobot;

import junit.framework.TestCase;

/**
 * generic test case used for scenarios that robot has been initialized but not
 * been placed
 * 
 * @author bill
 * 
 */
public class NotPlacedTestCase extends TestCase {
    protected TableRobot mBot;

    public NotPlacedTestCase() {
        mBot = new TableRobot(5);
        assertNotNull(mBot);
    }
}
