package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;
import com.impinj.octane.OctaneSdkException;

public abstract class TagReader {

    public abstract void start() throws OctaneSdkException;

    public abstract void stop() throws OctaneSdkException;

    public void setTagReadListener(TagReadListener listener) {
        tagReadListener = listener;
    }

    protected void notifyListener(TagRead tagRead) {
        tagReadListener.onRead(tagRead);
    }

    protected TagReadListener tagReadListener;

}
