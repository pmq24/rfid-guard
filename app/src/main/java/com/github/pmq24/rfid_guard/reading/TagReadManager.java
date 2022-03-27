package com.github.pmq24.rfid_guard.reading;

import java.util.LinkedList;
import java.util.List;

public class TagReadManager {

    public TagReadManager() {
        tagReadListeners = new LinkedList<>();
    }

    public void addTagReadListener(TagReadListener listener) {
        tagReadListeners.add(listener);
    }

    public void removeTagReadListener(TagReadListener listener) {
        tagReadListeners.remove(listener);
    }

    private void notifyListeners(TagReadDto read) {
        for (TagReadListener listener : tagReadListeners) {
           listener.onRead(read);
        }
    }

    private final List<TagReadListener> tagReadListeners;

}
