package com.billapp.cashman.vault;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import com.billapp.cashman.Cashman;
import com.billapp.cashman.Code;
import com.billapp.cashman.Displayer;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;

public class VaultDevice implements VaultI {
    public class VaultLoader implements Runnable {

        @Override
        public void run() {
            // do a lot of things
            if (loadTime > 0) {
                Cashman.sleep(loadTime);
            }
            postInitialize();
        }
    }

    private static VaultDevice instance;

    private static final int vaultId = 7001;

    public static synchronized VaultDevice getInstance() {
        if (null == instance) {
            instance = new VaultDevice();
        }
        return instance;
    }

    private VaultController controller = null;

    private long loadTime = 0;

    /**
     * a list of currency pending to be initialized
     */
    private List<Currency> pendingList;

    private VaultStateEnum state = VaultStateEnum.POWERED_OFF;

    private VaultData vaultData;

    private List<Currency> dispensedNoteList;

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#addNotes(java.util.List)
     */
    @Override
    public void addNotes(List<Currency> noteList) {
        vaultData.addNotes(noteList);
    }

    /**
     * forward/proxy controller's add-observer request to vault-data object
     * 
     * @param o
     *            an object of observer to be added to vault-data
     */
    void forwardObserver(Observer o) {
        vaultData.addObserver(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#getAvailablility()
     */
    @Override
    public Map<CurrencyEnum, Integer> getAvailablility() {
        if (state == VaultStateEnum.INITIALIZING) {
            // controller is not ready, populate vault-data directly
            return vaultData.getStorageData();
        }

        if (state == VaultStateEnum.INITIALIZED && null != getControler()) {
            return getControler().getAvailability();
        }

        // null controller or unsupported state
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#getControler()
     */
    @Override
    public VaultController getControler() {
        if (null == controller) {
            controller = new VaultController(this);
        }
        return controller;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#getState()
     */
    @Override
    public VaultStateEnum getState() {
        return state;
    }

    /**
     * @see #initialize(List)
     */
    public void initialize() {
        initialize(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#initialize(java.util.List)
     */
    @Override
    public void initialize(List<Currency> noteList) {
        pendingList = noteList;
        // initialize vault-data
        vaultData = new VaultData();
        // set state before initialize
        setState(VaultStateEnum.INITIALIZING);
        // loading of the vault could take some time
        VaultLoader vi = new VaultLoader();
        Thread thr = new Thread(vi);
        thr.start();
    }

    /**
     * final step for vault initialization, this method is called as last step
     * of vault-loader (in sub thread)
     */
    private void postInitialize() {
        if (null == pendingList) {
            getControler().initialize(vaultId);
        } else {
            getControler().initialize(vaultId, pendingList);
            vaultData.addNotes(pendingList);
            pendingList = null;
        }
        // add controller as observer to vault-data
        vaultData.addObserver(getControler());

        // then set state
        setState(VaultStateEnum.INITIALIZED);
        Displayer.getInstance().display(Code.OK_DEV_INIT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#removeNotes(java.util.List)
     */
    @Override
    public void removeNotes(List<Currency> noteList) {
        // TODO not impl
    }

    /**
     * @see #request(int, int)
     */
    public void request(int dollarAmount) {
        request(dollarAmount, 0);
    }

    /**
     * process a user request of certain amount of dollar and cent
     * 
     * It should be able to dispense legal combinations of notes.
     * 
     * For example, a request for $100 can be satisfied by either five $20 notes
     * or 2 $50 notes. It is not required to present a list of options.
     * 
     * If a request can not be satisfied due to failure to find a suitable
     * combination of notes, it should report an error condition in some
     * fashion. For example, in an ATM with only $20 and $50 notes, it is not
     * possible to dispense $30.
     * 
     * Dispensing money should reduce the amount of available cash in the
     * machine.
     * 
     * Failure to dispense money due to an error should not reduce the amount of
     * available cash in the machine.
     * 
     * @param dollarAmount
     *            an integer of request amount for dollar
     * @param centAmount
     *            an integer of request amount for cent
     */
    public synchronized void request(int dollarAmount, int centAmount) {

        Displayer.getInstance().display(
                String.format("requesting %s.%s", dollarAmount, centAmount));

        if (VaultStateEnum.INITIALIZED == state) {
            // proceed request
            getControler().withdraw(dollarAmount, centAmount);

        } else {
            // wrong state to process request
            Displayer.getInstance().display(Code.NOT_READY);
        }
    }

    /**
     * back door method for testing purpose, simulate the loading delay of vault
     * initialization
     * 
     * @param loadTime
     *            a long integer of loading vault loading time estimation
     */
    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public void setState(VaultStateEnum newState) {
        state = newState;
    }

    public void shutdown() {
        Displayer.getInstance().display("shutting down vault");
        if (null != vaultData) {
            getControler().shutdown();
            vaultData.deleteObservers();

            // another a lot of things to do
        }
        instance = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#dispense(java.util.List)
     */
    @Override
    public void dispense(List<Currency> payload) {

        Displayer.getInstance().display("dispensing");
        dispensedNoteList = payload;

        for (Currency note : payload) {
            Displayer.getInstance().display(note.toString());
        }

        vaultData.removeNotes(payload);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.billapp.cashman.vault.VaultI#getDispensedNoteList()
     */
    @Override
    public List<Currency> getDispensedNoteList() {
        return dispensedNoteList;
    }

}
