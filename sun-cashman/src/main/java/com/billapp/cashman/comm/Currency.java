package com.billapp.cashman.comm;

public class Currency {
    private int count;
    private CurrencyEnum type;

    public Currency(CurrencyEnum denoteType, int denoteCount) {
        super();
        this.type = denoteType;
        this.count = denoteCount;
    }

    public int getCount() {
        return count;
    }

    public CurrencyEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Currency [count=" + count + ", type=" + type + "]";
    }

}
