package com.billapp.cashman.obs;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.billapp.cashman.Code;
import com.billapp.cashman.Displayer;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;
import com.billapp.cashman.vault.VaultData;
import com.billapp.cashman.vault.VaultDevice;

public class LowReSupplier implements Observer {

    private int noteReSupplyCount;
    private int noteThreshold;
    private CurrencyEnum noteType;

    public LowReSupplier(int vaultId, CurrencyEnum type, int miniCount,
            int reSupplyCount) {
        noteType = type;
        noteThreshold = miniCount;
        noteReSupplyCount = reSupplyCount;
    }

    private void resupply() {
        Displayer.getInstance().display(Code.OK_RE_SUPPLY);

        List<Currency> noteList = new ArrayList<Currency>();
        noteList.add(new Currency(noteType, noteReSupplyCount));

        VaultDevice.getInstance().addNotes(noteList);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof VaultData) {
            VaultData vd = (VaultData) o;
            if (!vd.getStorageData().containsKey(noteType)
                    || vd.getStorageData().get(noteType) <= noteThreshold) {
                resupply();
            }
        }
    }
}
