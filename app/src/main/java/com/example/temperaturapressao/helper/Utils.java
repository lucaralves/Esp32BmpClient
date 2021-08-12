package com.example.temperaturapressao.helper;

import android.content.Context;

public class Utils {

    public static final int ACTIVITY_MODE_TEMPERATURA            = 0;
    public static final int ACTIVITY_MODE_PRESSAO           = 1;

    public static final int UNKNOWN_ACTION = 2;

    public static String getWSAddress(Context context) {

        return "http://192.168.1.120:80";
    }
}
