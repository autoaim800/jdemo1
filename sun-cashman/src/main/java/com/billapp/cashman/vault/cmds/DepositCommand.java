package com.billapp.cashman.vault.cmds;

import com.billapp.cashman.Code;

public class DepositCommand extends GenericCommand implements CommandI {

    @Override
    public void execute() {
        // check capacity
        returnCode = Code.OK;
    }

}
