package com.github.pmq24.rfid_guard.alarming;

import com.github.pmq24.rfid_guard.data.TagRead;

public interface Alarm {

    void alarm(TagRead tagRead);
}
