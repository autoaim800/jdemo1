package com.billapp.cashman.vault;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import com.billapp.cashman.comm.CurrencyEnum;

public class MockData extends VaultData {

    private Map<CurrencyEnum, Integer> storageData = new HashMap<CurrencyEnum, Integer>();

    /**
     * warning of distinct currency
     */
    private Set<CurrencyEnum> warnSet = new HashSet<CurrencyEnum>();

    public Set<CurrencyEnum> getWarnSet() {
        return warnSet;
    }

    public Map<CurrencyEnum, Integer> getStorageData() {
        return storageData;
    }

    public MockData(Map<CurrencyEnum, Integer> availMap) {
        for (CurrencyEnum type : availMap.keySet()) {
            storageData.put(type, availMap.get(type));
        }
    }

    public void addWarn(CurrencyEnum noteType) {
        warnSet.add(noteType);
    }

}
