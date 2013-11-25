package com.billsoft.jtoyrobot.core;

/**
 * 2D surface interface
 * 
 * @author bill
 * 
 */
public interface Surface2DI {
    /**
     * verify whether given 2d coordinate is with in the surface
     * 
     * @param x
     *            an integer of axis-x
     * @param y
     *            an integer of axis-y
     * @return a boolean of true or false indicates whether its within surface
     */
    public boolean isInside(int x, int y);

    /**
     * validate whether given direction is valid
     * 
     * @param dir
     *            an integer of direction from <code>RobotConst</code>
     * @return a boolean true indicates given direction is valid, false
     *         otherwise
     */
    public boolean isValidDirection(int dir);

    /**
     * verify whether the surface is a table which robot can run inside
     * 
     * @return a boolean indicates whether its a valid area for robot to run
     *         within
     */
    public boolean isTable();
    
    public int getWidth();
    
    public int getLength();
    
    public int[] getDirs();
    
}
