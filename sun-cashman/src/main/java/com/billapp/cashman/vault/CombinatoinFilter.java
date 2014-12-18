package com.billapp.cashman.vault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.billapp.cashman.Code;
import com.billapp.cashman.Helper;
import com.billapp.cashman.comm.CurrencyEnum;
import com.billapp.cashman.vault.cmds.NoteCountCmp;
import com.billapp.cashman.vault.cmds.WarnCmp;
import com.billapp.cashman.vault.cmds.WithdrawSimCommand;

/**
 * combination/s filter methods are stored within this class
 */
public class CombinatoinFilter {

    /**
     * store a copy of availability-map of currency/s
     */
    protected Map<CurrencyEnum, Integer> avaMap;
    protected List<WithdrawSimCommand> filteredList;
    protected List<WithdrawSimCommand> possibleList;
    /**
     * stores a copy of threshold-map
     */
    protected Map<CurrencyEnum, Integer> thresMap;

    public CombinatoinFilter(Map<CurrencyEnum, Integer> availabilityMap,
            Map<CurrencyEnum, Integer> thresholdMap) {
        avaMap = availabilityMap;
        thresMap = thresholdMap;
    }

    /**
     * filter possible-list into filtered-list
     */
    public List<WithdrawSimCommand> filterPossibleList() {

        // filter by error
        List<WithdrawSimCommand> pending = new ArrayList<WithdrawSimCommand>();
        for (WithdrawSimCommand cmd : possibleList) {
            if (cmd.getReturnCode() == Code.OK) {
                // only keep command with return-code==OK
                pending.add(cmd);
            }
        }

        // filter by warning-count
        TreeSet<WithdrawSimCommand> sorted = new TreeSet<WithdrawSimCommand>(
                new WarnCmp());
        sorted.addAll(pending);

        WithdrawSimCommand wcmd = sorted.first();
        int minWarnCount = wcmd.getWarnCount();

        // only command/s with minimum warning is/are to be considered
        pending = new ArrayList<WithdrawSimCommand>();
        for (WithdrawSimCommand cmd : sorted) {
            if (cmd.getWarnCount() <= minWarnCount) {
                pending.add(cmd);
            } else {
                Helper.info("skip due to warn");
            }
        }

        // filter by total-note-count
        // current preference is to dispense as few note as possible
        sorted = new TreeSet<WithdrawSimCommand>(new NoteCountCmp());
        sorted.addAll(pending);

        pending = new ArrayList<WithdrawSimCommand>();
        for (WithdrawSimCommand cmd : sorted) {
            if (cmd.getPayloadCount() > 0) {
                pending.add(cmd);
            } else {
                Helper.info("skip due to count");
            }
        }

        return pending;

    }

    /**
     * return filtered list for testing purpose
     */
    public List<WithdrawSimCommand> getFilteredList() {
        return filteredList;
    }

    public WithdrawSimCommand getFirst() {
        if (null == filteredList || filteredList.size() < 1) {
            return null;
        }
        return filteredList.get(0);
    }

}
