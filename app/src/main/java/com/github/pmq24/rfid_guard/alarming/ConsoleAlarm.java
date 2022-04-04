package com.github.pmq24.rfid_guard.alarming;

import com.github.pmq24.rfid_guard.database.tag_reads.TagReadRecord;

public class ConsoleAlarm implements Alarm {

    @Override
    public void alarm(TagReadRecord tagReadRecord) {
        System.out.println();
        System.out.println("==< NOT PURCHASED >======================================================================");
        System.out.println(tagReadRecord);
        System.out.println("=========================================================================================");
        System.out.println();
    }
}
