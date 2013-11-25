package com.billsoft.jtoyrobot.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.billsoft.jtoyrobot.RobotConsoleI;
import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;
import com.billsoft.jtoyrobot.core.RobotI;
import com.billsoft.jtoyrobot.core.impl.TableRobot;

/**
 * class works as console which controls the robot
 * 
 * @author bill
 * 
 */
public class RobotConsole implements RobotConsoleI {

	/**
	 * <pre>
	 * java -cp bin com.billsoft.jtoyrobot.impl.RobotConsole  scr1.txt
	 * java -cp bin com.billsoft.jtoyrobot.impl.RobotConsole < scr2.txt
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RobotI bot = new TableRobot(5);
		RobotConsole rc = new RobotConsole(bot);
		if (1 > args.length) {
			rc.executeStdIn();
		} else {
			rc.executeFile(args[0]);
		}
	}

	private RobotI mBot;

	/**
	 * local copy of last known position of last report command
	 */
	private Position mLastKnownPosition = null;

	public RobotConsole(RobotI bot) {
		mBot = bot;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * <pre>
	 * PLACE X,Y,F
	 * MOVE
	 * LEFT
	 * RIGHT
	 * REPORT
	 * </pre>
	 * 
	 * @see com.billsoft.jtoyrobot.RobotConsoleI#command(java.lang.String)
	 */
	@Override
	public boolean command(String cmd) {
		if (RobotHelper.CMD_LEFT.equals(cmd)) {
			return mBot.left();
		}
		if (RobotHelper.CMD_MOVE.equals(cmd)) {
			return mBot.move();
		}
		if (RobotHelper.CMD_RIGHT.equals(cmd)) {
			return mBot.right();
		}
		if (RobotHelper.CMD_REPORT.equals(cmd)) {
			Position pos = mBot.report();
			if (null == pos) {
				return false;
			}
			mLastKnownPosition = pos;
			System.out.println(String.format("Output: %s",
					mLastKnownPosition.toString()));
			return true;

		}
		if (cmd.startsWith(RobotHelper.CMD_PLACE)) {
			String params = cmd.substring(RobotHelper.CMD_PLACE.length() + 1);
			String[] subs = params.split(",");
			if (3 == subs.length) {
				try {
					int x = Integer.parseInt(subs[0]);
					int y = Integer.parseInt(subs[1]);
					String face = subs[2];
					return mBot.place(x, y, RobotHelper.obtainIntRepr(face));
				} catch (NumberFormatException e) {
					RobotHelper.error("invalid number format: " + cmd);
				}
			}
			// unknown format
		}
		return false;
	}

	private void executeFile(String filePathName) {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(filePathName));
			executeStream(is);
		} catch (FileNotFoundException e) {
			RobotHelper.error("no found input file:" + filePathName);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// ignored
				}
			}
		}
	}

	private void executeStdIn() {
		executeStream(System.in);
	}

	private void executeStream(InputStream is) {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(is));
			String input = new String();
			while ((input = stdin.readLine()) != null) {
				list.add(input);
			}
		} catch (IOException e) {
			// should not happen
			RobotHelper.error(e.getMessage());
		}
		for (String s : list) {
			command(s);
		}
	}

	@Override
	public Position getLastKnownPosition() {
		return mLastKnownPosition;
	}
}
