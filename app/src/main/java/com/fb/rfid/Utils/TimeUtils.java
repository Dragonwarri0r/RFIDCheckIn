package com.fb.rfid.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    Date date = new Date();

    String time = date.toLocaleString();


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年-MM月dd日");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH时mm分ss秒");

    public String getDate() {
        return dateFormat.format(date);
    }

    public String getTime(){
        return timeFormat.format(date);
    }

}
