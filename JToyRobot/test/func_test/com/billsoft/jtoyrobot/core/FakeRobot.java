package com.billsoft.jtoyrobot.core;

import com.billsoft.jtoyrobot.core.Position;
import com.billsoft.jtoyrobot.core.RobotHelper;
import com.billsoft.jtoyrobot.core.RobotI;
import com.billsoft.jtoyrobot.core.Surface2DI;

/**
 * a fake robot, does not do a thing, for robot console test purpose
 * 
 * @author bill
 * 
 */
public class FakeRobot implements RobotI {

    private String lastCommand;

    public String getLastCommand() {
        return lastCommand;
    }

    @Override
    public boolean left() {
        lastCommand = RobotHelper.CMD_LEFT;
        return true;
    }

    @Override
    public boolean right() {
        lastCommand = RobotHelper.CMD_RIGHT;
        return true;
    }

    @Override
    public boolean move() {
        lastCommand = RobotHelper.CMD_MOVE;
        return true;
    }

    @Override
    public Position report() {
        lastCommand = RobotHelper.CMD_REPORT;
        return new Position(0, 0, RobotHelper.EAST);
    }

    @Override
    public boolean place(int x, int y, int face) {
        lastCommand = String.format("%s %s,%s,%s", x, y, RobotHelper.CMD_PLACE,
                RobotHelper.obtainStrRepr(face));
        return true;
    }

}
