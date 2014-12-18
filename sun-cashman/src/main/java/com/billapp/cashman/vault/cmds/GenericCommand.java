package com.billapp.cashman.vault.cmds;

import java.util.List;

import com.billapp.cashman.Code;
import com.billapp.cashman.comm.Currency;

public class GenericCommand {
    protected int centAmount;
    protected int dollarAmount;
    protected List<Currency> payload;
    protected int returnCode = Code.ERROR;

    public List<Currency> getPayload() {
        return payload;
    }

    public int getPayloadCount() {
        int sum = 0;
        for (Currency note : payload) {
            sum += note.getCount();
        }
        return sum;
    }

    public int getPayloadSum() {
        int sum = 0;
        for (Currency note : payload) {
            sum += note.getCount() * note.getType().value;
        }
        return sum;
    }

    public int getReturnCode() {
        return returnCode;
    }

}
