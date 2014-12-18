package com.billapp.cashman.vault;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.comm.CurrencyEnum;

public class VaultDeviceRichTest extends CashmanTestCase {

    @Before
    public void setUp() throws Exception {
        initVaultALot();
    }

    @Test
    public void testRequestD20() {


        vault.request(20);
        assertEquals(20, Cashman.getSummary(vault.getDispensedNoteList()));

        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Cashman.getSummary(am1);

        assertEquals(sum0, sum1 + 20);
    }

    @Test
    public void testRequestD40() {
        
        
        vault.request(40);
        assertEquals(40, Cashman.getSummary(vault.getDispensedNoteList()));
        
        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Cashman.getSummary(am1);
        assertEquals(sum0, sum1 + 40);
    }

    @Test
    public void testRequestD50() {
        vault.request(50);
        assertEquals(50, Cashman.getSummary(vault.getDispensedNoteList()));
        
        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Cashman.getSummary(am1);
        assertEquals(sum0, sum1 + 50);
    }

    @Test
    public void testRequestD70() {
        vault.request(70);
        assertEquals(70, Cashman.getSummary(vault.getDispensedNoteList()));
        
        Map<CurrencyEnum, Integer> am1 = vault.getAvailablility();
        int sum1 = Cashman.getSummary(am1);
        assertEquals(sum0, sum1 + 70);
    }

    @Test
    public void testRequestD80() {
        vault.request(80);
        assertEquals(80, Cashman.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD100() {
        vault.request(100);
        assertEquals(100, Cashman.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD150() {
        vault.request(150);
        assertEquals(150, Cashman.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD60() {
        vault.request(60);
        assertEquals(60, Cashman.getSummary(vault.getDispensedNoteList()));
    }

    @Test
    public void testRequestD110() {
        vault.request(110);
        assertEquals(110, Cashman.getSummary(vault.getDispensedNoteList()));
    }

}
