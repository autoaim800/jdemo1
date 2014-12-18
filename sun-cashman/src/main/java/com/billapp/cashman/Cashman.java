package com.billapp.cashman;


import com.billapp.cashman.vault.VaultDevice;

public class Cashman {
    public static void main(String[] args) {

        VaultDevice vd = VaultDevice.getInstance();
        vd.initialize(Helper.buildNoteList("6x20 5x50"));
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);
        Helper.printMc(vd.getAvailablility());

        vd.request(20);
        vd.request(40);
        vd.request(50);
        vd.request(70);
        vd.request(80);
        vd.request(100);
        vd.request(150);
        vd.request(60);
        vd.request(110);
        vd.shutdown();

        vd = VaultDevice.getInstance();
        vd.initialize(Helper.buildNoteList("8x20 3x50"));
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);
        Helper.printMc(vd.getAvailablility());
        vd.request(200);
        vd.shutdown();
        
        vd = VaultDevice.getInstance();
        vd.initialize(Helper.buildNoteList("5x20 3x50"));
        Helper.sleep(Conf.TIMEOUT_DEV_WARN_UP);
        Helper.printMc(vd.getAvailablility());
        vd.request(100);
        vd.shutdown();
        
        
    }
}
