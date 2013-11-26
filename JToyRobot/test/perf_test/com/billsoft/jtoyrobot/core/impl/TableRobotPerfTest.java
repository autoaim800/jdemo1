package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.PerfTestCase;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * test robot's move ability on large table
 * 
 * @author bill
 * 
 */
public class TableRobotPerfTest extends PerfTestCase {

    private TableRobot mBot;

    public TableRobotPerfTest() {
        mBot = new TableRobot(LOOP_ROUND + 10);
        assertNotNull(mBot);
        assertNotNull(mBot.getTable());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mBot.place(0, 0, RobotHelper.EAST);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testTableRobot() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < LOOP_ROUND; i++) {
            mBot = new TableRobot(i + 10);
        }
        long t2 = System.currentTimeMillis();
        assertTrue(t2 - t1 < 1000);
    }

    public void testLeft() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < LOOP_ROUND; i++) {
            mBot.left();
        }
        long t2 = System.currentTimeMillis();
        assertTrue(t2 - t1 < 1000);
    }

    public void testRight() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < LOOP_ROUND; i++) {
            mBot.right();
        }
        long t2 = System.currentTimeMillis();
        assertTrue(t2 - t1 < 1000);
    }

    public void testMove() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < LOOP_ROUND; i++) {
            mBot.move();
        }
        long t2 = System.currentTimeMillis();
        assertTrue(t2 - t1 < 1000);
    }

    public void testReport() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < LOOP_ROUND; i++) {
            mBot.report();
        }
        long t2 = System.currentTimeMillis();
        assertTrue(t2 - t1 < 1000);
    }

    public void testPlace() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < LOOP_ROUND; i++) {
            mBot.place(i, i, RobotHelper.EAST);
        }
        long t2 = System.currentTimeMillis();
        assertTrue(t2 - t1 < 1000);
    }

}
