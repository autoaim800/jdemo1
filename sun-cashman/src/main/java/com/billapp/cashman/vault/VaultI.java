package com.billapp.cashman.vault;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public interface VaultI {

    public abstract void forwardObserver(Observer o);

    /**
     * add given list of notes into vault, assume vault has infinite capacity
     * and all note/coin are acceptable
     * 
     * 
     * After initialization, it is only possible to add or remove notes
     * 
     * @param noteList
     *            a list of <code>Currency</code>
     */
    public abstract void addNotes(List<Currency> noteList);

    public abstract void dispense(List<Currency> payload);

    /**
     * It must know how many of each type of bank note it has. It should be able
     * to report back how much of each note it has.
     * 
     * It should be possible to tell it that it has so many of each type of note
     * during initialization.
     * 
     * @return a list of <code>Currency</code> which currently available
     */
    public abstract Map<CurrencyEnum, Integer> getAvailablility();

    public abstract VaultController getControler();

    /**
     * method for debugging purpose
     */
    public abstract List<Currency> getDispensedNoteList();

    public abstract VaultStateEnum getState();

    /**
     * initialize the vault with given list of note, given list of note can be
     * null
     * 
     * It should be possible to tell it that it has so many of each type of note
     * during initialization.
     * 
     * After initialization, it is only possible to add or remove notes.
     * 
     * @param noteList
     *            a list of <code>Currency</code>, null for empty vault
     * 
     */
    public abstract void initialize(List<Currency> noteList);

    /**
     * remove given list of notes from vault after initialization after succeed
     * on availability check
     * 
     * After initialization, it is only possible to add or remove notes
     * 
     * @param noteList
     *            a list of <code>Currency</code> to be removed
     */
    public void removeNotes(List<Currency> noteList);

    public void request(int dollarAmount, int centAmount);
}