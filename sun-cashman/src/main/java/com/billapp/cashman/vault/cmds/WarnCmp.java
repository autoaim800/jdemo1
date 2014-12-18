package com.billapp.cashman.vault.cmds;

import java.util.Comparator;

import com.billapp.cashman.comm.CurrencyEnum;

/**
 * ascend order
 */
public class WarnCmp implements Comparator<WithdrawSimCommand> {

    @Override
    public int compare(WithdrawSimCommand left, WithdrawSimCommand right) {
        // in descent order
        return left.getWarnCount() - right.getWarnCount();
    }
}
