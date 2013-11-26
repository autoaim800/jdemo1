package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.MockRobot;
import com.billsoft.jtoyrobot.core.NotPlacedTestCase;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;

/**
 * table robot comparison test, real robot vs mock robot
 * 
 * @author bill
 * 
 */
public class TableRobotCmpTest extends NotPlacedTestCase {

    private MockRobot mMock;

    public TableRobotCmpTest() {
        super();
        mMock = new MockRobot(INIT_WIDTH);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCmpLeft() {
        assertTrue(mBot.place(INIT_X, INIT_Y, RobotHelper.EAST));
        assertTrue(mMock.place(INIT_X, INIT_Y, RobotHelper.EAST));

        assertTrue(mBot.left());
        assertTrue(mMock.left());

        Position pos1 = mBot.report();
        Position pos2 = mMock.report();

        assertNotNull(pos1);
        assertNotNull(pos2);

        assertEquals(pos2, pos1);
    }

    public void testCmpRight() {
        assertTrue(mBot.place(INIT_X, INIT_Y, RobotHelper.EAST));
        assertTrue(mMock.place(INIT_X, INIT_Y, RobotHelper.EAST));

        assertTrue(mBot.right());
        assertTrue(mMock.right());

        Position pos1 = mBot.report();
        Position pos2 = mMock.report();

        assertNotNull(pos1);
        assertNotNull(pos2);

        assertEquals(pos2, pos1);
    }

    public void testCmpMove() {
        assertTrue(mBot.place(INIT_X, INIT_Y, RobotHelper.EAST));
        assertTrue(mMock.place(INIT_X, INIT_Y, RobotHelper.EAST));

        assertTrue(mBot.move());
        assertTrue(mMock.move());

        Position pos1 = mBot.report();
        Position pos2 = mMock.report();

        assertNotNull(pos1);
        assertNotNull(pos2);

        assertEquals(pos2, pos1);
    }

    public void testCmpReport() {
        assertTrue(mBot.place(INIT_X, INIT_Y, RobotHelper.EAST));
        assertTrue(mMock.place(INIT_X, INIT_Y, RobotHelper.EAST));

        Position pos1 = mBot.report();
        Position pos2 = mMock.report();

        assertNotNull(pos1);
        assertNotNull(pos2);

        assertEquals(pos2, pos1);
    }

    public void testCmpPlace() {

        assertTrue(mBot.place(INIT_X, INIT_Y, RobotHelper.EAST));
        assertTrue(mMock.place(INIT_X, INIT_Y, RobotHelper.EAST));

        Position pos1 = mBot.report();
        Position pos2 = mBot.report();

        assertNotNull(pos1);
        assertNotNull(pos2);

        assertEquals(pos2, pos1);
    }

}
