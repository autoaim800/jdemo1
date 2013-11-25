package com.billsoft.jtoyrobot;

import com.billsoft.jtoyrobot.core.Position;

/**
 * interface of robot console. robot console does not care about the command
 * content, it reads command/s from stdin or file then forwards them to robot,
 * it ups to the robot to decide whether the command should be executed or
 * ignored
 * 
 * @author bill
 * 
 */
public interface RobotConsoleI {
	/**
	 * accepts string command for robot operation, returns the status of command
	 * execution
	 * 
	 * @param cmd
	 *            a string or robot command
	 * @return a boolean, true indicates whether the given command has been
	 *         executed, false indicates the given command has been ignored
	 */
	public boolean command(String cmd);

	/**
	 * returns the last know position of the robot of report command
	 * 
	 * @return a object of <code>Position</code>, null if report has not been
	 *         invoked
	 */
	public Position getLastKnownPosition();

}
