package com.billsoft.jtoyrobot.core;

import com.billsoft.jtoyrobot.core.impl.TableRobot;

/**
 * spy robot invokes real-robot, used for console test purpose
 * 
 * @author bill
 * 
 */
public class SpyRobot implements RobotI {

    private boolean mB;
    private Position mP;
    private TableRobot mRealBot;

    public SpyRobot(int width) {
        mRealBot = new TableRobot(width);
    }

    public Position getPosition() {
        return mP;
    }

    public boolean isSuccess() {
        return mB;
    }

    @Override
    public boolean left() {
        mB = mRealBot.left();
        return mB;
    }

    @Override
    public boolean move() {
        mB = mRealBot.move();
        return mB;
    }

    @Override
    public boolean place(int x, int y, int face) {
        mB = mRealBot.place(x, y, face);
        return mB;
    }

    @Override
    public Position report() {
        mP = mRealBot.report();
        return mP;
    }

    @Override
    public boolean right() {
        mB = mRealBot.right();
        return mB;
    }

}
