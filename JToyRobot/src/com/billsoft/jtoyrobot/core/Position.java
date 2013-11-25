package com.billsoft.jtoyrobot.core;

/**
 * value object contains: coordinate and facing
 * 
 * @author bill
 * 
 */
public class Position {

    private int mX;
    private int mY;
    private int mDir;

    public Position(int axisX, int axisY, int direction) {
        this.mX = axisX;
        this.mY = axisY;
        this.mDir = direction;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getDir() {
        return mDir;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", mX, mY, RobotHelper.obtainStrRepr(mDir));
    }

}
