package com.billsoft.jtoyrobot.core;

/**
 * a robot does invoke any table, for robot console test purpose
 * 
 * @author bill
 * 
 */
public class MockRobot implements RobotI {

    private int mWidth;
    private boolean mPlaced = false;
    private int mDir;
    private int mY;
    private int mX;

    public MockRobot(int width) {
        mWidth = width;
    }

    @Override
    public boolean left() {
        if (mPlaced) {
            mDir--;
            mDir %= 4;
            return true;
        }
        return false;
    }

    @Override
    public boolean right() {
        if (mPlaced) {
            mDir++;
            mDir %= 4;
            return true;
        }
        return false;
    }

    @Override
    public boolean move() {
        if (mPlaced) {
            int x = mX;
            int y = mY;
            switch (mDir) {
            case 0:
                x++;
                break;
            case 1:
                y--;
                break;
            case 2:
                x--;
                break;
            case 3:
                y++;
                break;
            default:
                return false;
            }
            mX = x;
            mY = y;
            return true;
        }
        return false;
    }

    @Override
    public Position report() {
        if (mPlaced) {
            return new Position(mX, mY, mDir);
        }
        return null;
    }

    @Override
    public boolean place(int x, int y, int face) {
        if (face >= 0 && face <= 4 && x >= 0 && x < mWidth && y >= 0
                && y < mWidth) {
            mPlaced = true;
            mX = x;
            mY = y;
            mDir = face;
        }
        return mPlaced;
    }
}
