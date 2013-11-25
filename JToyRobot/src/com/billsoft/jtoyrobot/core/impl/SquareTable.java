package com.billsoft.jtoyrobot.core.impl;

import com.billsoft.jtoyrobot.core.Surface2DI;

/**
 * square table implementation
 * 
 * @author bill
 * 
 */
public class SquareTable implements Surface2DI {

    private int[] mDirs;
    private int mWidth;

    public SquareTable(int[] directions, int squareWidth) {
        mWidth = squareWidth;
        mDirs = directions;
    }

    public int[] getDirs() {
        return mDirs;
    }

    @Override
    public int getLength() {
        return mWidth;
    }

    public int getWidth() {
        return mWidth;
    }

    @Override
    public boolean isInside(int x, int y) {
        return x >= 0 && x < mWidth && y >= 0 && y < mWidth;
    }

    @Override
    public boolean isTable() {
        return mWidth > 0;
    }

    @Override
    public boolean isValidDirection(int dir) {
        for (int d : mDirs) {
            if (d == dir) {
                return true;
            }
        }
        return false;
    }

}
