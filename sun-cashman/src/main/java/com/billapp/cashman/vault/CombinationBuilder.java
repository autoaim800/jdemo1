package com.billapp.cashman.vault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.Conf;
import com.billapp.cashman.Displayer;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;
import com.billapp.cashman.comm.CurrencyEnumComparator;
import com.billapp.cashman.comm.UnitEnum;
import com.billapp.cashman.vault.cmds.WithdrawSimCommand;

/**
 * calculate combination base on given availability-map, threshold-map and
 * requested amount
 * 
 */
public class CombinationBuilder extends CombinatoinFilter {
    public static class Combination {
        private int rest;

        public int getRest() {
            return rest;
        }

        public List<Currency> getNoteList() {
            return noteList;
        }

        private List<Currency> noteList;

        public Combination(int rest, List<Currency> noteList) {
            super();
            this.rest = rest;
            this.noteList = noteList;
        }

    }

    private int centAmount = 0;

    private int dollarAmount = 0;

    public List<WithdrawSimCommand> getPossibleList() {
        return possibleList;
    }

    public CombinationBuilder(Map<CurrencyEnum, Integer> availabilityMap,
            Map<CurrencyEnum, Integer> thresholdMap) {
        super(availabilityMap, thresholdMap);
    }

    private List<WithdrawSimCommand> buildPossibleCmdList() {
        // calculate available note types and count
        Map<CurrencyEnum, Integer> dollarMap = new HashMap<CurrencyEnum, Integer>();
        Map<CurrencyEnum, Integer> centMap = new HashMap<CurrencyEnum, Integer>();

        for (CurrencyEnum type : avaMap.keySet()) {
            if (UnitEnum.CENT == type.unit) {
                centMap.put(type, avaMap.get(type));
            } else {
                // TODO only supports hard coded dollar and cent for now
                dollarMap.put(type, avaMap.get(type));
            }
        }

        List<WithdrawSimCommand> retList = buildPossibleUnit1List(dollarAmount,
                dollarMap, UnitEnum.DOLLAR);

        return retList;
    }

    private List<WithdrawSimCommand> buildPossibleUnit1List(int amount,
            Map<CurrencyEnum, Integer> dollarMap, UnitEnum unit) {

        // sort currency-type by value desc
        TreeSet<CurrencyEnum> sortedTypeSet = new TreeSet<CurrencyEnum>(
                new CurrencyEnumComparator());
        sortedTypeSet.addAll(dollarMap.keySet());

        CurrencyEnum miniNoteType = sortedTypeSet.last();
        int miniNoteValue = miniNoteType.value;
        if (UnitEnum.CENT == unit) {
            miniNoteValue = miniNoteType.centValue;
        }

        List<WithdrawSimCommand> pendingCmd = new ArrayList<WithdrawSimCommand>();

        // ONE copy of currently noteList being working on
        // its order by note-value descent (big->small)
        List<Currency> pendingNote = new ArrayList<Currency>();

        Combination comb = estabilishBaseLine(amount, dollarMap, unit,
                sortedTypeSet, pendingNote);

        if (0 == comb.getRest()) {
            pendingCmd.add(new WithdrawSimCommand(comb.getNoteList(), avaMap,
                    thresMap));
        }

        // only try max-retry time/s
        for (int i = 0; i < Conf.MAX_RETRY; i++) {

            // break one bigger note into smaller one
            comb = replaceWithSmallerOnBaseLine(amount, dollarMap, unit,
                    sortedTypeSet, comb.getNoteList(), comb.getRest());

            if (0 == comb.getRest()) {
                pendingCmd.add(new WithdrawSimCommand(comb.getNoteList(),
                        avaMap, thresMap));
            }
        }

        return pendingCmd;
    }

    /**
     * on or less than threshold will trigger warning
     */
    public WithdrawSimCommand buildWithdrawCommand() {
        Displayer.getInstance().display("build withdraw for:" + dollarAmount);

        possibleList = buildPossibleCmdList();

        simulateWithdraw();

        filter();

        WithdrawSimCommand cmd = getFirst();

        return cmd;

    }

    /**
     * try to cope from the big-to-small note, may not be suitable to
     * request-amount, but at least can establish a base-line
     * 
     * @param pendingNote
     *            in-out parameter, always keeps last record of combination,
     *            even unsuccessful
     * @return remaining unbreakable amount, 0 for suitable combination
     */
    private Combination estabilishBaseLine(int amount,
            Map<CurrencyEnum, Integer> dollarMap, UnitEnum unit,
            final TreeSet<CurrencyEnum> sortedTypeSet,
            List<Currency> pendingNote) {

        Cashman.info("estabilish base-line for:" + amount);

        int rest = amount;

        for (CurrencyEnum type : sortedTypeSet) {

            int noteValue = type.value;
            if (UnitEnum.CENT == unit) {
                noteValue = type.centValue;
            }

            Integer noteCount = dollarMap.get(type);
            int tellCount = 0;
            while (rest >= noteValue && noteCount > 0) {
                tellCount += 1;
                noteCount -= 1;
                dollarMap.put(type, noteCount);
                rest -= noteValue;
            }

            if (tellCount > 0) {
                // add current currency to pending for output
                pendingNote.add(new Currency(type, tellCount));
            }
        }

        return new Combination(rest, pendingNote);
    }

    /**
     * merge given adjustment into given pending-note
     * 
     * @param pendingNote
     *            in-out parameter of pending-note
     * @param adjustment
     *            a list of adjustment
     * @param sortedTypeSet
     * @return a refreshed pending-note-list
     */
    private List<Currency> mergeToPending(final List<Currency> pendingNote,
            final List<Currency> adjustment,
            final TreeSet<CurrencyEnum> sortedTypeSet) {
        Map<CurrencyEnum, Integer> pm = lcToMc(pendingNote);
        Map<CurrencyEnum, Integer> am = lcToMc(adjustment);

        List<Currency> retList = new ArrayList<Currency>();
        for (CurrencyEnum type : sortedTypeSet) {
            int sum = 0;
            if (pm.containsKey(type)) {
                sum += pm.get(type);
            }
            if (am.containsKey(type)) {
                sum += am.get(type);
            }
            if (sum > 0) {
                retList.add(new Currency(type, sum));
            }
        }
        return retList;
    }

    /**
     * covert list-currency into map-currency
     */
    private Map<CurrencyEnum, Integer> lcToMc(List<Currency> lc) {
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

    /**
     * @param amount
     * @param dollarMap
     *            original available dollar map without knowledge of pendingNote
     * @param unit
     * @param sortedTypeSet
     * @param pendingNote
     *            in-out parameter, always keeps last record of combination,
     *            even unsuccessful, order by note-value descent
     * @param rest
     *            previous rest-amount
     * @return newly calculated rest-amount, 0 for suitable
     */
    private Combination replaceWithSmallerOnBaseLine(int amount,
            Map<CurrencyEnum, Integer> dollarMap, UnitEnum unit,
            final TreeSet<CurrencyEnum> sortedTypeSet,
            final List<Currency> pendingNote, int rest) {

        if (null == pendingNote || pendingNote.size() < 1) {
            // no previous base-line?
            return new Combination(rest, pendingNote);
        }

        List<Currency> adjustment = new ArrayList<Currency>();

        // obtain the big note (from left most of pending-note)
        Currency bigNote = pendingNote.get(0);
        // int newCount = bigNote.getCount() - 1;

        CurrencyEnum bigNoteType = bigNote.getType();
        int bigNoteValue = bigNoteType.value;
        if (UnitEnum.CENT == unit) {
            bigNoteValue = bigNoteType.centValue;
        }

        // return one big note to pool, write back to dollar-map
        Integer noteCount = dollarMap.get(bigNoteType);
        dollarMap.put(bigNote.getType(), noteCount + 1);

        // ready to overwrite pending-note
        // pendingNote.remove(0);
        adjustment.add(new Currency(bigNoteType, -1));
        // if (newCount > 0) {
        // Currency newBigNote = new Currency(bigNoteType, newCount);
        // pendingNote.add(0, newBigNote);
        // }

        // increase rest
        rest += bigNoteValue;

        // begin roll-calculation
        // but only for the rest from note-value smaller
        // than big-note-value

        for (CurrencyEnum type : sortedTypeSet) {

            int noteValue = type.value;
            if (UnitEnum.CENT == unit) {
                noteValue = type.centValue;
            }

            if (noteValue >= bigNoteValue) {
                // skip for current note (as done in base-line)
                continue;
            }

            noteCount = dollarMap.get(type);
            int tellCount = 0;
            while (rest >= noteValue && noteCount > 0) {
                tellCount += 1;
                noteCount -= 1;
                dollarMap.put(type, noteCount);
                rest -= noteValue;
            }

            if (tellCount > 0) {
                // add current currency to adjustment for output
                adjustment.add(new Currency(type, tellCount));
            }
        }

        return new Combination(rest, mergeToPending(pendingNote, adjustment,
                sortedTypeSet));
    }

    public void setCentAmount(int centAmount) {
        this.centAmount = centAmount;
    }

    public void setDollarAmount(int dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    private void simulateWithdraw() {
        for (WithdrawSimCommand cmd : possibleList) {
            cmd.execute();
        }
    }
}
