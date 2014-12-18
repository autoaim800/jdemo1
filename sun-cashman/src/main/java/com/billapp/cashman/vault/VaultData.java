package com.billapp.cashman.vault;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class VaultData extends Observable {

    protected Map<CurrencyEnum, Integer> storageData = new HashMap<CurrencyEnum, Integer>();

    public VaultData() {
    }

    /**
     * put one type of note into vault, do not check capacity
     * 
     * @param note
     *            an object of <code>Currency</code>
     */
    private void add(Currency note) {
        if (storageData.containsKey(note.getType())) {
            int currCount = storageData.get(note.getType());
            storageData.put(note.getType(), currCount + note.getCount());
        } else {
            storageData.put(note.getType(), note.getCount());
        }
    }

    public void addNotes(List<Currency> noteList) {
        for (Currency note : noteList) {
            add(note);
        }
        setChanged();
        notifyObservers();
    }

    public Map<CurrencyEnum, Integer> getStorageData() {
        return storageData;
    }

    /**
     * remove one type of note out of vault, do not check availability, must use
     * together with {@link #hasNote(CurrencyEnum, int)}
     * 
     * @param note
     *            an object of <code>Currency</code>
     */
    private void remove(Currency note) {
        
        if (null == note || null == note.getType()) {
            Cashman.info("null note?");
            return;
        }
        if (null == storageData) {
            Cashman.info("null storage data?");
            return;
        }
        if (null == storageData.get(note.getType())) {
            Cashman.info("no fund for:"  + note.getType());
            Cashman.printMc(storageData);
            return;
        }
        int currCount = storageData.get(note.getType());
        storageData.put(note.getType(), currCount - note.getCount());
    }

    public void removeNotes(List<Currency> noteList) {
        for (Currency note : noteList) {
            remove(note);
        }
        setChanged();
        notifyObservers();
    }

}
