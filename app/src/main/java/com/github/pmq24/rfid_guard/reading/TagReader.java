package com.github.pmq24.rfid_guard.reading;

import java.util.LinkedList;
import java.util.List;

public abstract class TagReader {

    public abstract void start();

    public abstract void stop();

    public void addTagReadListener(TagReadListener listener) {
        tagReadListeners.add(listener);
    }

    protected TagReader() {
        tagReadListeners = new LinkedList<>();
    }

    protected void notifyListeners(TagReadDto read) {
        for (TagReadListener listener : tagReadListeners) {
           listener.onRead(read);
        }
    }

    protected final List<TagReadListener> tagReadListeners;

}
