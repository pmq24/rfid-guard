package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;

public interface TagReadListener {

    void onRead(TagRead tagRead);

}
