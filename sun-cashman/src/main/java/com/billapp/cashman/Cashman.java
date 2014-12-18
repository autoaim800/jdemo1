package com.billapp.cashman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;
import com.billapp.cashman.comm.CurrencyEnumComparator;

public class Cashman {
    public static void main(String[] args) {

        Set<CurrencyEnum> sortedSet = new TreeSet<CurrencyEnum>(
                new CurrencyEnumComparator());
        Map<CurrencyEnum, Integer> dollarMap = new HashMap<CurrencyEnum, Integer>();

        dollarMap.put(CurrencyEnum.COIN_2, 11);
        dollarMap.put(CurrencyEnum.NOTE_50, 5);
        dollarMap.put(CurrencyEnum.NOTE_100, 7);
        dollarMap.put(CurrencyEnum.NOTE_20, 6);

        sortedSet.addAll(dollarMap.keySet());

        for (CurrencyEnum rank : sortedSet) {
            System.out.println(rank);
        }

        // VaultDevice vd = VaultDevice.getInstance();
        // vd.initialize();
        // System.out.println(vd.getState());
        // sleep(1200);
        // System.out.println(vd.getState());

    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void info(String s) {
        System.out.println(s);
    }

    public static void printMc(Map<CurrencyEnum, Integer> am) {
        info("Mc:");
        for (CurrencyEnum type : am.keySet()) {
            info(String.format("%s x%s", type, am.get(type)));
        }
    }

    public static void printLc(List<Currency> payload) {
        info("Lc:");
        for (Currency cmd : payload) {
            info(cmd.toString());
        }
    }

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
}
