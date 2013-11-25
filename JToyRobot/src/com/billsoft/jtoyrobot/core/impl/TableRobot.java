package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;
import com.billsoft.jtoyrobot.core.RobotI;
import com.billsoft.jtoyrobot.core.Surface2DI;

public class TableRobot implements RobotI {

    private Surface2DI mTable;
    private int mDir;
    private int mY;
    private int mX;
    private boolean mPlaced = false;

    public Surface2DI getTable() {
        return mTable;
    }

    public TableRobot(int width) {
        mTable = new SquareTable(RobotHelper.DIR4, width);
    }

    @Override
    public boolean left() {
        if (mPlaced) {
            if (RobotHelper.EAST == mDir) {
                mDir = RobotHelper.NORTH;
            } else {
                mDir -= 1;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean right() {
        if (mPlaced) {
            if (RobotHelper.NORTH == mDir) {
                mDir = RobotHelper.EAST;
            } else {
                mDir += 1;
            }
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
            case RobotHelper.EAST:
                x++;
                break;
            case RobotHelper.SOUTH:
                y--;
                break;
            case RobotHelper.WEST:
                x--;
                break;
            case RobotHelper.NORTH:
                y++;
                break;
            default:
                // unkonwn direction
                return false;
            }
            // out of switch
            if (mTable.isInside(x, y)) {
                mX = x;
                mY = y;
                return true;
            }
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
        if (mTable.isInside(x, y) && mTable.isValidDirection(face)) {
            mX = x;
            mY = y;
            mDir = face;
            mPlaced = true;
        }
        return mPlaced;
    }

}
