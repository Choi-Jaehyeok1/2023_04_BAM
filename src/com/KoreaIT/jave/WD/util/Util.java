package com.KoreaIT.jave.WD.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

    public static String getDate() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
        return dayTime.format(new Date(time));
    }

    public static String getDateTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dayTime.format(new Date(time));
    }

}