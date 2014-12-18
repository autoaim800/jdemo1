package com.billapp.cashman;

import java.util.ArrayDeque;
import java.util.Deque;

public class Displayer {

    private static Displayer instance;
    private Deque<String> msgs;

    public Deque<String> getMsgs() {
        return msgs;
    }

    private Displayer() {
        msgs = new ArrayDeque<String>();
    }

    public static Displayer getInstance() {
        if (null == instance) {
            instance = new Displayer();
        }
        return instance;
    }

    private static void writeln(String s) {
        System.out.println(s);

    }

    public void display(int code) {
        String msg;
        switch (code) {
        case Code.FAIL_INIT:
            msg = "failed to initialize vault";
            break;
        case Code.NOT_EXECUTE:
            msg = "it has not been executed";
            break;
        case Code.OK:
            msg = "it has been executed successfully";
            break;
        case Code.OK_DEV_INIT:
            msg = "device initialized";
            break;
        case Code.OK_DEV_OFF:
            msg = "device shutted down";
            break;
        case Code.OK_CTL_OBS:
            msg = "controller +obs";
            break;
        case Code.OK_CTL_OFF:
            msg = "controller shutted down";
            break;
        case Code.OK_RE_SUPPLY:
            msg = "re-supplied done";
            break;
            
        case Code.OK_CTL_UPDATE:
            msg = "update controller done";
            break;

        case Code.FAIL_COMBINATION:
            msg = "failed to calculate combination";
            return;

        case Code.FAIL_AMOUNT_VALID:
            msg = "invalid amount";
            return;

        default:
            msg = "unknown code:" + code;
            break;
        }

        writeln(msg);

        // TODO to be improved to delivery more information
        msgs.addLast(String.valueOf(code));
        if (msgs.size() > Conf.MAX_MSG_QUE_SIZE) {
            for (int i = 0; i < Conf.MAX_RETRY; i++) {
                msgs.removeFirst();
            }
        }

    }

    public void display(String s) {
        writeln(s);
    }
}
