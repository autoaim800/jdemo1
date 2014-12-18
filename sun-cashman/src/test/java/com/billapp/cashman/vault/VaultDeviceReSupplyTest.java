package com.billapp.cashman.vault;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.Code;
import com.billapp.cashman.Conf;
import com.billapp.cashman.Displayer;

public class VaultDeviceReSupplyTest {

    private VaultDevice vault;

    @Before
    public void setUp() throws Exception {
        vault = VaultDevice.getInstance();
        assertNotNull(vault);
        vault.initialize();
        Cashman.sleep(Conf.TIMEOUT_DEV_WARN_UP);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReSupplyLog() {
        fail("not impl");
        assertTrue(Displayer.getInstance().getMsgs()
                .contains(Code.OK_RE_SUPPLY));
    }

    @Test
    public void testReSupplyByBalance() {
        fail("not impl");

        // original balance

        // do some huge withdraw

        // assert new balance
    }
    
    @Test
    public void testWithdrawWithWarning() {
        fail("not impl");

        // original balance

        // do some huge withdraw

        // assert new balance
    }
}
