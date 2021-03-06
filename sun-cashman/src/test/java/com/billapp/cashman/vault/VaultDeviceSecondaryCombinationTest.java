package com.billapp.cashman.vault;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.Conf;
import com.billapp.cashman.Helper;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class VaultDeviceSecondaryCombinationTest extends CashmanTestCase {

    @Before
    public void setUp() throws Exception {
        initVaultEmpty();
    }

    @Test
    public void testRequestD100WithWarning() {

        List<Currency> noteList = new ArrayList<Currency>();
        noteList.add(new Currency(CurrencyEnum.NOTE_20, 5));
        noteList.add(new Currency(CurrencyEnum.NOTE_50, 1));

        vault.initialize(noteList);
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);

        vault.request(100);
        assertEquals(100, Helper.getSummary(vault.getDispensedNoteList()));

        // availability still greater than threshold
        Map<CurrencyEnum, Integer> am = vault.getAvailablility();
        assertTrue(am.get(CurrencyEnum.NOTE_20) <= Conf.THRESHOLD_NOTE_20);

        Helper.sleep(1000);
    }

    @Test
    public void testRequestD200AvoidWarning() {

        List<Currency> noteList = new ArrayList<Currency>();
        noteList.add(new Currency(CurrencyEnum.NOTE_20, 8));
        noteList.add(new Currency(CurrencyEnum.NOTE_50, 3));

        vault.initialize(noteList);
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);

        vault.request(200);
        assertEquals(200, Helper.getSummary(vault.getDispensedNoteList()));

        // availability still greater than threshold
        Map<CurrencyEnum, Integer> am = vault.getAvailablility();
        assertTrue(am.get(CurrencyEnum.NOTE_20) > Conf.THRESHOLD_NOTE_20);
    }

}
