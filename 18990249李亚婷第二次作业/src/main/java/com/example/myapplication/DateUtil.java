package com.example.myapplication;

import  android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getNowDateTime(){
        SimpleDateFormat s_format = new SimpleDateFormat();
        return s_format.format(new Date());
    }

    public static String getNowTime(){
        SimpleDateFormat s_format = new SimpleDateFormat();
        return s_format.format(new Date());
    }
}