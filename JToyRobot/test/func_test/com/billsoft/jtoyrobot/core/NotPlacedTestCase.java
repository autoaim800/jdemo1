package com.billsoft.jtoyrobot.core;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.impl.TableRobot;

/**
 * generic test case used for scenarios that robot has been initialized but not
 * been placed
 * 
 * @author bill
 * 
 */
public class NotPlacedTestCase extends TestCase {
	protected static final int INIT_WIDTH = 5;
	protected static final int INIT_Y = 0;
	protected static final int INIT_X = 0;
	protected TableRobot mBot;

	public NotPlacedTestCase() {
		mBot = new TableRobot(INIT_WIDTH);
		assertNotNull(mBot);
		assertNotNull(mBot.getTable());
	}
}
