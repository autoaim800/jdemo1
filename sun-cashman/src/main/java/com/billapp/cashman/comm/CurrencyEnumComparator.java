package com.billapp.cashman.comm;

import java.util.Comparator;

/**
 * sort currency in descent order
 */
public class CurrencyEnumComparator implements Comparator<CurrencyEnum> {

    @Override
    public int compare(CurrencyEnum left, CurrencyEnum right) {
        // in descent order
        return right.centValue - left.centValue;
    }
}
