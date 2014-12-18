package com.billapp.cashman.vault;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class VaultDeviceInitializedTest extends CashmanTestCase {

    @Before
    public void setUp() throws Exception {
        super.initVaultEmpty();
    }

    @Test
    public void testDeposit() {
        List<Currency> noteList = new ArrayList<Currency>();
        noteList.add(new Currency(CurrencyEnum.NOTE_20, 30));
        noteList.add(new Currency(CurrencyEnum.NOTE_50, 50));
        vault.addNotes(noteList);

        Map<CurrencyEnum, Integer> retMap = vault.getAvailablility();
        assertNotNull("null availability", retMap);
        noteListVsNoteMap(noteList, retMap);
    }

    @Test
    public void testRequestNeg() {
        Map<CurrencyEnum, Integer> ret1 = vault.getAvailablility();
        assertNotNull("null availability", ret1);
        vault.request(97);

        Map<CurrencyEnum, Integer> ret2 = vault.getAvailablility();
        assertNotNull("null availability", ret2);

        assertEquals(ret1, ret2);
    }

}
