package com.billsoft.jtoyrobot.core;

public class RobotHelper {

	public static int[] DIR4 = new int[] { RobotHelper.EAST, RobotHelper.SOUTH,
			RobotHelper.WEST, RobotHelper.NORTH };

	public static int[] DIR6 = new int[] { RobotHelper.EAST, RobotHelper.SOUTH,
			RobotHelper.WEST, RobotHelper.NORTH, 100, 101 };

	public final static int EAST = 0;
	public final static int SOUTH = 1;
	public final static int WEST = 2;
	public final static int NORTH = 3;
	public final static int UP = 4;
	public final static int DOWN = 5;

	public final static String S_EAST = "EAST";
	public final static String S_SOUTH = "SOUTH";
	public final static String S_WEST = "WEST";
	public final static String S_NORTH = "NORTH";
	public final static String S_UP = "UP";
	public final static String S_DOWN = "DOWN";

	public static final String CMD_RIGHT = "RIGHT";
	public static final String CMD_PLACE = "PLACE";
	public static final String CMD_MOVE = "MOVE";
	public static final String CMD_LEFT = "LEFT";
	public static final String CMD_REPORT = "REPORT";
	public static final String S_UNKNOWN = "UNKNOWN";

	public static final String CMD_PLACE_00E = "PLACE 0,0,EAST";

	public static String obtainStrRepr(int dir) {
		switch (dir) {
		case EAST:
			return S_EAST;
		case SOUTH:
			return S_SOUTH;
		case WEST:
			return S_WEST;
		case NORTH:
			return S_NORTH;
		case UP:
			return S_UP;
		case DOWN:
			return S_DOWN;
		}
		return S_UNKNOWN;
	}

	public static int obtainIntRepr(String face) {
		if (S_NORTH.equals(face)) {
			return NORTH;
		}
		if (S_WEST.equals(face)) {
			return WEST;
		}
		if (S_EAST.equals(face)) {
			return EAST;
		}
		if (S_SOUTH.equals(face)) {
			return SOUTH;
		}
		return -1;
	}

	public static void error(String s) {
		System.err.println(s);
	}
}
