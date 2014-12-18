package com.billapp.cashman.vault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;
import com.billapp.cashman.vault.cmds.WithdrawSimCommand;

public class CombinationBuilderTest extends CashmanTestCase {

    @Before
    public void setUp() throws Exception {
        super.initVaultEmpty();
    }

    @Test
    public void testBuildWithdrawD200() {

        List<Currency> noteList = new ArrayList<Currency>();
        noteList.add(new Currency(CurrencyEnum.NOTE_20, 8));
        noteList.add(new Currency(CurrencyEnum.NOTE_50, 3));
        vault.addNotes(noteList);

        Map<CurrencyEnum, Integer> tm = new HashMap<CurrencyEnum, Integer>();

        CombinationBuilder cb = new CombinationBuilder(
                vault.getAvailablility(), tm);
        cb.setDollarAmount(200);

        WithdrawSimCommand wcmd = cb.buildWithdrawCommand();

        List<WithdrawSimCommand> plist = cb.getPossibleList();
        assertNotNull(plist);

        for (WithdrawSimCommand cmd : plist) {
            assertEquals(200, cmd.getPayloadSum());
        }

        assertNotNull("no combination", wcmd);

        assertNotNull(wcmd.getPayload());
        assertEquals(200, wcmd.getPayloadSum());

    }
}
