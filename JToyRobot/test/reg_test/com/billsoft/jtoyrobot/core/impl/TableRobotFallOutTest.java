package com.billsoft.jtoyrobot.core.impl;

import junit.framework.TestCase;

import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;
import com.billsoft.jtoyrobot.core.StubTable;

/**
 * verify without proper guidance of the <code>SquareTable</code>, robot can
 * fall out
 * 
 * @author bill
 * 
 */
public class TableRobotFallOutTest extends TestCase {

    private SquareTable mReal;
    private StubTable mStub;
    private TableRobot mBot;

    public TableRobotFallOutTest() {
        // initialize stub table and real table with the same parameters
        mStub = new StubTable(RobotHelper.DIR4, 5);
        mReal = new SquareTable(RobotHelper.DIR4, 5);
        
        // assign stub table to robot
        mBot = new TableRobot(mStub);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPlaceOob() {
        // place out of boundary
        assertTrue(mBot.place(40, 50, RobotHelper.EAST));

        Position pos = mBot.report();
        assertNotNull(pos);

        // real table disagrees with the position
        assertFalse(mReal.isInside(pos.getX(), pos.getY()));
    }

    public void testLeftOob() {
        // place at boundary
        mBot.place(4, 4, RobotHelper.EAST);

        // keep moving at wall at direction of stub table
        assertTrue(mBot.move());
        assertTrue(mBot.move());

        // turn left
        assertTrue(mBot.left());

        Position pos = mBot.report();
        assertNotNull(pos);

        // real table disagrees with the position
        assertFalse(mReal.isInside(pos.getX(), pos.getY()));

        // direction should be valid
        assertEquals(RobotHelper.NORTH, pos.getDir());
    }

    public void testRight() {
        // place at boundary
        mBot.place(4, 4, RobotHelper.EAST);

        // keep moving at wall at direction of stub table
        assertTrue(mBot.move());
        assertTrue(mBot.move());

        // turn right
        assertTrue(mBot.right());

        Position pos = mBot.report();
        assertNotNull(pos);

        // real table disagrees with the position
        assertFalse(mReal.isInside(pos.getX(), pos.getY()));

        // direction should be valid
        assertEquals(RobotHelper.SOUTH, pos.getDir());
    }

    public void testMoveOob() {
        // place at boundary
        mBot.place(4, 4, RobotHelper.EAST);

        // keep moving at wall at direction of stub table
        assertTrue(mBot.move());
        assertTrue(mBot.move());

        Position pos = mBot.report();
        assertNotNull(pos);

        // real table disagrees with the position
        assertFalse(mReal.isInside(pos.getX(), pos.getY()));
    }
}
