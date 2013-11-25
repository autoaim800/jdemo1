package com.billsoft.jtoyrobot.core;

public interface RobotI {
	/**
	 * @return true if command has been executed, false if command has been
	 *         ignored
	 */
	public boolean left();

	/**
	 * @return true if command has been executed, false if command has been
	 *         ignored
	 */
	public boolean right();

	/**
	 * @return true if command has been executed, false if command has been
	 *         ignored
	 */
	public boolean move();

	/**
	 * @return true if command has been executed, null if command has been
	 *         ignored
	 */
	public Position report();

	/**
	 * @return true if the robot has been placed, false otherwise
	 */
	public boolean place(int x, int y, int face);
}
