package com.billapp.cashman.vault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.Code;
import com.billapp.cashman.comm.CurrencyEnum;
import com.billapp.cashman.vault.cmds.NoteCountCmp;
import com.billapp.cashman.vault.cmds.WarnCmp;
import com.billapp.cashman.vault.cmds.WithdrawSimCommand;

public class CombinatoinFilter {

    protected Map<CurrencyEnum, Integer> avaMap;
    protected Map<CurrencyEnum, Integer> thresMap;
    protected List<WithdrawSimCommand> filteredList;
    protected List<WithdrawSimCommand> possibleList;

    public List<WithdrawSimCommand> getFilteredList() {
        return filteredList;
    }

    public CombinatoinFilter(Map<CurrencyEnum, Integer> availabilityMap,
            Map<CurrencyEnum, Integer> thresholdMap) {
        avaMap = availabilityMap;
        thresMap = thresholdMap;
    }

    public void filter() {
        // filter by error
        filteredList = new ArrayList<WithdrawSimCommand>();
        for (WithdrawSimCommand cmd : possibleList) {
            if (cmd.getReturnCode() == Code.OK) {
                filteredList.add(cmd);
            }
        }

        // filter by warning-count
        TreeSet<WithdrawSimCommand> sorted = new TreeSet<WithdrawSimCommand>(
                new WarnCmp());
        sorted.addAll(filteredList);

        WithdrawSimCommand wcmd = sorted.first();
        int minWarnCount = wcmd.getWarnCount();

        filteredList = new ArrayList<WithdrawSimCommand>();
        for (WithdrawSimCommand cmd : sorted) {
            if (cmd.getWarnCount() <= minWarnCount) {
                filteredList.add(cmd);
            } else {
                Cashman.info("skip at warn");
            }
        }

        // filter by total-note-count
        sorted = new TreeSet<WithdrawSimCommand>(new NoteCountCmp());
        sorted.addAll(filteredList);

        filteredList = new ArrayList<WithdrawSimCommand>();
        for (WithdrawSimCommand cmd : sorted) {
            if (cmd.getPayloadCount() > 0) {
                filteredList.add(cmd);
            }else {
                Cashman.info("skip at count");
            }
        }

    }

    public WithdrawSimCommand getFirst() {
        if (null == filteredList || filteredList.size() < 1) {
            return null;
        }
        return filteredList.get(0);
    }

}
