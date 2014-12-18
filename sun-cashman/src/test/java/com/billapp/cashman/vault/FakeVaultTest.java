package com.billapp.cashman.vault;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.billapp.cashman.Helper;

public class FakeVaultTest {

    private FakeVault vault;

    @Before
    public void setUp() throws Exception {
        vault = new FakeVault();
        vault.initialize(Helper.buildNoteList("6x20 2x50"));
    }

    @Test
    public void testRequestD200() {
        vault.request(200, 0);
    }

}
