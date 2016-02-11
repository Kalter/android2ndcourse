package com.itis.androidlab.exchanger.models;

public enum Currency {

    RUB("руб."),
    USD("$"),
    EUR("€");

    private String mValue;

    Currency(String value) {
        mValue = value;
    }

    public static Currency getEnum(String value) {
        for (Currency v : values())
            if (v.toString().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }

    public static String[] getValuesArray() {
        Currency[] values = values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].toString();
        }
        return array;
    }


    @Override
    public String toString() {
        return mValue;
    }


}
