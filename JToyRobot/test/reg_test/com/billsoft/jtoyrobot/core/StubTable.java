package com.billsoft.jtoyrobot.core;

/**
 * an stub of table implementation, returns all default value
 * 
 * @author bill
 * 
 */
public class StubTable implements Surface2DI {
    private int[] mDirs;
    private int mWidth;

    public StubTable(int[] directions, int squareWidth) {
        mWidth = squareWidth;
        mDirs = directions;
    }

    @Override
    public boolean isInside(int x, int y) {
        return true;
    }

    @Override
    public boolean isValidDirection(int dir) {
        return true;
    }

    @Override
    public boolean isTable() {
        return true;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public int getLength() {
        return mWidth;
    }

    @Override
    public int[] getDirs() {
        return mDirs;
    }

}
