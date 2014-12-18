package com.billapp.cashman.vault;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.Helper;
import com.billapp.cashman.comm.CurrencyEnum;

public class VaultDeviceRichTest extends CashmanTestCase {

    @Before
    public void setUp() throws Exception {
        initVaultALot();
    }

    @Test
    public void testRequestD100() {
        vault.request(100);
        assertEquals(100, Helper.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD110() {
        vault.request(110);
        assertEquals(110, Helper.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD150() {
        vault.request(150);
        assertEquals(150, Helper.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD20() {

        vault.request(20);
        assertEquals(20, Helper.getSummary(vault.getDispensedNoteList()));

        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Helper.getSummary(am1);

        assertEquals(sum0, sum1 + 20);
    }

    @Test
    public void testRequestD40() {

        vault.request(40);
        assertEquals(40, Helper.getSummary(vault.getDispensedNoteList()));

        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Helper.getSummary(am1);
        assertEquals(sum0, sum1 + 40);
    }

    @Test
    public void testRequestD50() {
        vault.request(50);
        assertEquals(50, Helper.getSummary(vault.getDispensedNoteList()));

        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Helper.getSummary(am1);
        assertEquals(sum0, sum1 + 50);
    }

    @Test
    public void testRequestD60() {
        vault.request(60);
        assertEquals(60, Helper.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD70() {
        vault.request(70);
        assertEquals(70, Helper.getSummary(vault.getDispensedNoteList()));

        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Helper.getSummary(am1);
        assertEquals(sum0, sum1 + 70);
    }

    @Test
    public void testRequestD80() {
        vault.request(80);
        assertEquals(80, Helper.getSummary(vault.getDispensedNoteList()));
    }

}
