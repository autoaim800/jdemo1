package com.billapp.cashman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class Helper {

    public static int getSummary(List<Currency> payload) {
        int sum = 0;
        if (null != payload) {
            for (Currency note : payload) {
                sum += note.getCount() * note.getType().value;
            }
        }
        return sum;
    }

    public static int getSummary(Map<CurrencyEnum, Integer> am) {
        int sum = 0;
        if (null != am) {
            for (CurrencyEnum type : am.keySet()) {
                int noteValue = type.value;
                sum += am.get(type) * noteValue;
            }
        }
        return sum;
    }

    public static void info(String s) {
        System.out.println(s);
    }

    public static List<Currency> buildNoteList(String desc) {
        String[] subs = desc.split(" ");
        List<Currency> pending = new ArrayList<Currency>();
        for (String sub : subs) {
            String[] items = sub.split("x", 2);
            int count = Integer.parseInt(items[0].trim());
            int value = Integer.parseInt(items[1].trim());
    
            CurrencyEnum type = null;
            switch (value) {
            case 20:
                type = CurrencyEnum.NOTE_20;
                break;
            case 50:
                type = CurrencyEnum.NOTE_50;
                break;
            case 100:
                type = CurrencyEnum.NOTE_100;
                break;
            default:
                break;
            }
    
            if (null != type) {
                pending.add(new Currency(type, count));
            }
        }
        return pending;
    }

    public static void printLc(List<Currency> payload) {
        info("Lc:");
        for (Currency cmd : payload) {
            info(cmd.toString());
        }
    }

    public static void printMc(Map<CurrencyEnum, Integer> am) {
        info("Mc:");
        for (CurrencyEnum type : am.keySet()) {
            info(String.format("%s x%s", type, am.get(type)));
        }
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * covert list-currency into map-currency
     */
    public static Map<CurrencyEnum, Integer> lcToMc(List<Currency> lc) {
        Map<CurrencyEnum, Integer> ret = new HashMap<CurrencyEnum, Integer>();
        for (Currency note : lc) {
            if (ret.containsKey(note.getType())) {
                Integer currCount = ret.get(note.getType());
                ret.put(note.getType(), note.getCount() + currCount);
            } else {
                ret.put(note.getType(), note.getCount());
            }
        }
        return ret;
    }

}
