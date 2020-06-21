package com.hengheng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class data2 {

    public static String data2String(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
}
