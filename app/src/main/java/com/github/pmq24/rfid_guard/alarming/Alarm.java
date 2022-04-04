package com.github.pmq24.rfid_guard.alarming;

import com.github.pmq24.rfid_guard.database.tag_reads.TagReadRecord;

public interface Alarm {

    void alarm(TagReadRecord tagRead);
}
