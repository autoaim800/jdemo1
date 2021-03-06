package com.billapp.cashman.vault;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;

import com.billapp.cashman.Conf;
import com.billapp.cashman.Helper;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class CashmanTestCase {

    protected int sum0;

    protected VaultDevice vault;
    public void initVaultALot() {
        List<Currency> noteList = new ArrayList<Currency>();
        noteList.add(new Currency(CurrencyEnum.NOTE_20, 200));
        noteList.add(new Currency(CurrencyEnum.NOTE_50, 200));

        vault = VaultDevice.getInstance();
        assertNotNull(vault);

        vault.initialize(noteList);
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);

        // vault.addNotes(noteList);

        Map<CurrencyEnum, Integer> am0 = vault.getAvailablility();
        sum0 = Helper.getSummary(am0);
    }

    public void initVaultEmpty() {
        vault = VaultDevice.getInstance();
        assertNotNull(vault);
        vault.initialize();
        Helper.sleep(100);
    }

    protected void noteListVsNoteMap(List<Currency> noteList,
            Map<CurrencyEnum, Integer> noteMap) {
        assertNotNull(noteList);
        assertNotNull(noteMap);

        assertEquals("note type count mismatch", noteList.size(), noteMap
                .keySet().size());

        for (Currency note : noteList) {
            assertTrue("no such note-type", noteMap.containsKey(note.getType()));
            assertEquals("note count mismatch", new Integer(note.getCount()),
                    noteMap.get(note.getType()));
        }
    }

    @After
    public void tearDown() throws Exception {
        assertNotNull("null vault to shutdown?", vault);
        vault.shutdown();
        vault = null;
    }
}
