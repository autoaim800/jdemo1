package com.billapp.cashman.vault;

import java.util.Map;

import com.billapp.cashman.Displayer;
import com.billapp.cashman.comm.CurrencyEnum;

public class AmountValidator {

    private VaultController controller;

    public AmountValidator(VaultController vaultController) {
        controller = vaultController;
    }

    public boolean valid(int dollarAmount, int centAmount) {
        if (dollarAmount > controller.getDollarSum()) {

            return false;
        }
        if (centAmount > controller.getCentSum()) {
            return false;
        }

        // TODO to be impl

        // if dollar < mini-note-value => false

        // some other things

        return true;
    }

}
