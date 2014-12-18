package com.billapp.cashman.vault;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.Code;
import com.billapp.cashman.Displayer;

public class VaultControllerTest extends CashmanTestCase {

    @Before
    public void setUp() throws Exception {
        initVaultALot();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUpdateController() {
        vault.request(40);
        Displayer.getInstance().getMsgs()
                .contains(String.valueOf(Code.OK_CTL_UPDATE));
    }

    @Test
    public void testWithdraw100() {
        vault.getControler().withdraw(100, 0);
    }

}
