package com.github.pmq24.rfid_guard.reading;

public abstract class TagReader {

    public abstract void start();

    public abstract void stop();

    public void setTagReadListener(TagReadListener listener) {
        tagReadListener = listener;
    }

    protected void notifyListener(TagReadDto read) {
        tagReadListener.onRead(read);
    }

    protected TagReadListener tagReadListener;

}
