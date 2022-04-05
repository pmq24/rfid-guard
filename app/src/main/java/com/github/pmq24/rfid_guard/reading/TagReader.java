package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;

public abstract class TagReader {

    public abstract void start();

    public abstract void stop();

    public void setTagReadListener(TagReadListener listener) {
        tagReadListener = listener;
    }

    protected void notifyListener(TagRead tagRead) {
        tagReadListener.onRead(tagRead);
    }

    protected TagReadListener tagReadListener;

}
