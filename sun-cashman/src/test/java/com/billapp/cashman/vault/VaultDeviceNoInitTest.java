package com.billapp.cashman.vault;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.Conf;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class VaultDeviceNoInitTest extends CashmanTestCase {

    @Before
    public void setUp() throws Exception {
        vault = VaultDevice.getInstance();
        assertNotNull(vault);
    }

    @Test
    public void testGetAvailablility() {
        vault.initialize();
        Cashman.sleep(100);
        Map<CurrencyEnum, Integer> retMap = vault.getAvailablility();
        assertNotNull("null availability returned", retMap);
        assertTrue("newly init is NOT empty", retMap.keySet().size() < 1);
    }

    @Test
    public void testGetControler() {
        VaultController ctl = vault.getControler();
        assertNotNull(ctl);
    }

    @Test
    public void testInitialize() {
        assertEquals(VaultStateEnum.POWERED_OFF, vault.getState());
        vault.setLoadTime(300);
        vault.initialize();
        assertEquals(VaultStateEnum.INITIALIZING, vault.getState());
        Cashman.sleep(600);
        assertEquals(VaultStateEnum.INITIALIZED, vault.getState());
    }

    @Test
    public void testInitializeListOfCurrency() {

        List<Currency> noteList = new ArrayList<Currency>();
        noteList.add(new Currency(CurrencyEnum.NOTE_20, 30));
        noteList.add(new Currency(CurrencyEnum.NOTE_50, 50));
        vault.initialize(noteList);
        Cashman.sleep(Conf.TIMEOUT_DEV_WARN_UP);

        Map<CurrencyEnum, Integer> retMap = vault.getAvailablility();
        assertNotNull("null availability", retMap);

        noteListVsNoteMap(noteList, retMap);
    }
}
