package com.billapp.cashman.vault.cmds;

import java.util.List;
import java.util.Map;

import com.billapp.cashman.Code;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class WithdrawSimCommand extends GenericCommand implements CommandI {

    private Map<CurrencyEnum, Integer> availMap;

    private Map<CurrencyEnum, Integer> thresMap;

    private int warnCount = 0;

    public WithdrawSimCommand(List<Currency> noteList,
            Map<CurrencyEnum, Integer> availabilityMap,
            Map<CurrencyEnum, Integer> thresholds) {
        payload = noteList;
        availMap = availabilityMap;
        thresMap = thresholds;
    }

    @Override
    public void execute() {
        for (Currency note : payload) {

            if (!availMap.containsKey(note.getType())) {
                returnCode = Code.NO_SUCH_NOTE;
                return;
            }

            Integer availCount = availMap.get(note.getType());
            if (availCount < note.getCount()) {
                returnCode = Code.NO_ENOUGH_NOTE;
                return;
            }

            if (thresMap.containsKey(note.getType())) {
                // this type of note has threshold
                if (availCount - note.getCount() <= thresMap
                        .get(note.getType())) {
                    // this type of note reaches threshold
                    // but CAN withdraw
                    warnCount += 1;
                }
            }
        }
        returnCode = Code.OK;
    }

    public int getWarnCount() {
        return warnCount;
    }

}
