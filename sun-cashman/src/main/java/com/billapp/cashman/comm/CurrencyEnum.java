package com.billapp.cashman.comm;

public enum CurrencyEnum {
    COIN_1(1, UnitEnum.DOLLAR), COIN_10(10, UnitEnum.CENT), COIN_2(2,
            UnitEnum.DOLLAR), COIN_20(20, UnitEnum.CENT), COIN_5(5,
            UnitEnum.CENT),

    COIN_50(50, UnitEnum.CENT), NOTE_05(5, UnitEnum.DOLLAR), NOTE_10(10,
            UnitEnum.DOLLAR), NOTE_100(100, UnitEnum.DOLLAR), NOTE_20(20,
            UnitEnum.DOLLAR), NOTE_50(50, UnitEnum.DOLLAR);

    public final UnitEnum unit;
    public final int value;
    public final int centValue;

    CurrencyEnum(int noteValue, UnitEnum noteUnit) {
        value = noteValue;
        unit = noteUnit;

         if (UnitEnum.DOLLAR == unit) {
         centValue = 100 * value;
         }else{
         centValue = value;
         }
    }

}
