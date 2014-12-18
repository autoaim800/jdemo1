package com.billapp.cashman.vault;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import com.billapp.cashman.Helper;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

/**
 * fake vault always has a lot of money
 */
public class FakeVault implements VaultI {

    private List<Currency> dispensedNoteList;
    private Map<CurrencyEnum, Integer> storageData;
    private VaultData vd;
    private VaultController controller;

    @Override
    public void addNotes(List<Currency> noteList) {
        // no need to add, already has a lot

    }

    @Override
    public void dispense(List<Currency> payload) {
        Helper.printLc(payload);
        dispensedNoteList = payload;

    }

    @Override
    public Map<CurrencyEnum, Integer> getAvailablility() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VaultController getControler() {
        if (null == controller) {
            controller = new VaultController(this);
        }
        return controller;
    }

    @Override
    public List<Currency> getDispensedNoteList() {
        return dispensedNoteList;
    }

    @Override
    public VaultStateEnum getState() {
        // always ready
        return VaultStateEnum.INITIALIZED;
    }

    @Override
    public void initialize(List<Currency> noteList) {

        vd = new VaultData();
        vd.addNotes(noteList);
        getControler().initialize(-1, noteList);
        // fake vault with real controller
        vd.addObserver(getControler());
    }

    @Override
    public void removeNotes(List<Currency> noteList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void request(int dollarAmount, int centAmount) {
        getControler().withdraw(dollarAmount, centAmount);
    }

    @Override
    public void forwardObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }

}
