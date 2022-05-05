package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;
import com.impinj.octane.OctaneSdkException;

public abstract class TagReader {
// hàm chạy thiết bị
    public abstract void start() throws OctaneSdkException;
// hàm ngưng thiết bị
    public abstract void stop() throws OctaneSdkException;
// hàm setTagReadListener sẽ được gọi khi có tag đi qua 
    public void setTagReadListener(TagReadListener listener) {
        tagReadListener = listener;
    }
// hàm notifyListener thông báo tag đi qua thiết bị 
    protected void notifyListener(TagRead tagRead) {
        tagReadListener.onRead(tagRead);
    }

    protected TagReadListener tagReadListener;

}
