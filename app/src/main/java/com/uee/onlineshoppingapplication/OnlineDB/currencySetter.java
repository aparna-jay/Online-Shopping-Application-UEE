package com.uee.onlineshoppingapplication.OnlineDB;

import android.content.Context;
import android.content.res.Resources;

public class currencySetter {


    private static float value=1;
    private static String c_type;

    public static float getValue() {
        return value;
    }

    public static void setValue(float value) {
        currencySetter.value = value;
    }

    public static String getC_type() {
        return c_type;
    }

    public static void setC_type(String c_type) {
        currencySetter.c_type = c_type;
    }
    public static void changeCurrency(String c_type) {
        if (c_type.equals("Rs")) {
            setValue(1);
        }
        if (c_type.equals("USD")) {
            setValue((float) 0.00555556);
        }
    }
}
