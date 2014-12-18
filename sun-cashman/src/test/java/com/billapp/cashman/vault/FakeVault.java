package com.billapp.cashman.vault;

import java.util.List;
import java.util.Map;

import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

/**
 * fake vault always has a lot of money
 */
public class FakeVault implements VaultI {

    @Override
    public void addNotes(List<Currency> noteList) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<CurrencyEnum, Integer> getAvailablility() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VaultController getControler() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VaultStateEnum getState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initialize(List<Currency> noteList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeNotes(List<Currency> noteList) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispense(List<Currency> payload) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Currency> getDispensedNoteList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void request(int dollarAmount, int centAmount) {
        // TODO Auto-generated method stub

    }

}
