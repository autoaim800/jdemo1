package com.billapp.cashman.vault.cmds;

import java.util.Comparator;

/**
 * ascend order
 */
public class NoteCountCmp implements Comparator<WithdrawSimCommand> {

    @Override
    public int compare(WithdrawSimCommand left, WithdrawSimCommand right) {
        // in descent order
        return left.getPayloadCount() - right.getPayloadCount();
    }
}
