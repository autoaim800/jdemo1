package com.billapp.cashman.vault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.billapp.cashman.Code;
import com.billapp.cashman.Conf;
import com.billapp.cashman.Displayer;
import com.billapp.cashman.comm.Currency;
import com.billapp.cashman.comm.CurrencyEnum;
import com.billapp.cashman.comm.UnitEnum;
import com.billapp.cashman.obs.LowReSupplier;
import com.billapp.cashman.vault.cmds.WithdrawSimCommand;

public class VaultController implements Observer {

    private Map<CurrencyEnum, Integer> availableNoteMap;
    private int vaultId;
    private List<Observer> observers;
    private HashMap<CurrencyEnum, Integer> thresholds;
    private VaultI vault;
    private int centSum;
    private int dollarSum;

    public int getCentSum() {
        return centSum;
    }

    public int getDollarSum() {
        return dollarSum;
    }

    public VaultController(VaultI vaultDevice) {
        vault = vaultDevice;
    }

    public HashMap<CurrencyEnum, Integer> getThresholds() {
        return thresholds;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    /**
     * The controller needs to be able to inform other interested objects of
     * activity. Threshold notification in particular is desirable, so that the
     * ATM can be re-supplied before it runs out of cash.
     * 
     * @param vaultData
     *            an object of <code>VaultData</code>
     */
    private void applyObservers() {

        VaultDevice vault = VaultDevice.getInstance();

        thresholds = new HashMap<CurrencyEnum, Integer>();
        thresholds.put(CurrencyEnum.NOTE_20, Conf.THRESHOLD_NOTE_20);
        thresholds.put(CurrencyEnum.NOTE_50, Conf.THRESHOLD_NOTE_50);

        observers = new ArrayList<Observer>();

        for (CurrencyEnum type : thresholds.keySet()) {
            Integer threshold = thresholds.get(type);
            observers.add(new LowReSupplier(vaultId, type, threshold,
                    Conf.COUNT_RE_SUPPLY));
        }

        for (Observer o : observers) {
            vault.forwardObserver(o);
        }

        Displayer.getInstance().display(Code.OK_CTL_OBS);
    }

    public Map<CurrencyEnum, Integer> getAvailability() {
        return availableNoteMap;
    }

    public void initialize(int vaultId) {
        initialize(vaultId, null);
    }

    /**
     * It can be kept in memory. However, it should go through a distinct
     * Initialization period where it is told the current available amounts
     * prior to being used.
     * 
     * Persistence of the controller is optional at this time.
     * 
     * low-re-supplier observer does NOT work during initialization, works only
     * when vault-data changes after initialization
     * 
     * @param vaultId
     *            an integer of vault-id
     * 
     * @param noteList
     *            a list of <code>Currency</code>
     */
    public void initialize(int hostVaultId, List<Currency> noteList) {
        vaultId = hostVaultId;
        availableNoteMap = new HashMap<CurrencyEnum, Integer>();

        if (null != noteList) {
            for (Currency note : noteList) {
                if (availableNoteMap.containsKey(note.getType())) {
                    Integer currCount = availableNoteMap.get(note.getType());
                    availableNoteMap.put(note.getType(), note.getCount()
                            + currCount);
                } else {
                    availableNoteMap.put(note.getType(), note.getCount());
                }
            }
        }

        applyObservers();

        postUpdate();

    }

    /**
     * Persistence of the controller is optional at this time.
     */
    public void shutdown() {
        Displayer.getInstance().display(Code.OK_CTL_OFF);
    }

    @Override
    public void update(Observable o, Object arg) {
        // wipe out existing availability
        availableNoteMap = new HashMap<CurrencyEnum, Integer>();

        if (o instanceof VaultData) {

            VaultData vd = (VaultData) o;

            for (CurrencyEnum type : vd.getStorageData().keySet()) {
                availableNoteMap.put(type, vd.getStorageData().get(type));
            }

            postUpdate();

            Displayer.getInstance().display(Code.OK_CTL_UPDATE);
        }
    }

    private void postUpdate() {
        for (CurrencyEnum type : availableNoteMap.keySet()) {
            if (UnitEnum.CENT == type.unit) {
                centSum += type.centValue * availableNoteMap.get(type);
            } else {
                dollarSum += type.value * availableNoteMap.get(type);
            }
        }
    }

    public synchronized void withdraw(int dollarAmount, int centAmount) {

        Displayer.getInstance().display("trying to withdraw:" + dollarAmount);
        AmountValidator validator = new AmountValidator(this);
        if (!validator.valid(dollarAmount, centAmount)) {
            Displayer.getInstance().display(Code.FAIL_AMOUNT_VALID);
            return;
        }

        Displayer.getInstance().display("calculating for:" + dollarAmount);
        CombinationBuilder cb = new CombinationBuilder(getAvailability(),
                getThresholds());
        cb.setDollarAmount(dollarAmount);
        cb.setCentAmount(centAmount);

        WithdrawSimCommand cmd = cb.buildWithdrawCommand();

        if (null == cmd) {
            Displayer.getInstance().display("null cmd for:" + dollarAmount);
            Displayer.getInstance().display(Code.FAIL_COMBINATION);
        } else {
            Displayer.getInstance().display(
                    "invoking vault for:" + dollarAmount);
            vault.dispense(cmd.getPayload());
        }

    }

}
