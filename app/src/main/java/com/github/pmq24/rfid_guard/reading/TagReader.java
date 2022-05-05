package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;
import com.impinj.octane.OctaneSdkException;

public abstract class TagReader {
// hàm chạy thiết bị
    public abstract void start() throws OctaneSdkException;
// hàm ngưng thiết bị
    public abstract void stop() throws OctaneSdkException;
// hàm setTagReadListener 
    public void setTagReadListener(TagReadListener listener) {
        tagReadListener = listener;
    }

    protected void notifyListener(TagRead tagRead) {
        tagReadListener.onRead(tagRead);
    }

    protected TagReadListener tagReadListener;

}
