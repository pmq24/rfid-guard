package com.github.pmq24.rfid_guard.alarming;

import com.github.pmq24.rfid_guard.data.TagRead;

public class ConsoleAlarm implements Alarm {

    @Override
    public void alarm(TagRead tagRead) {
        System.out.println();
        System.out.println("==< NOT PURCHASED >======================================================================");
        System.out.println(tagRead);
        System.out.println("=========================================================================================");
        System.out.println();
    }
}
