package com.github.pmq24.rfid_guard.reading;

import java.util.List;
import java.util.Random;

public class PredefinedTagReader extends TagReader {

    private final Thread tagReadGeneratingThread;

    public PredefinedTagReader(List<String> tagList, int intervalInSeconds) {
        super();

        this.tagList = tagList;

        tagReadGeneratingThread = new Thread() {

            @Override
            public void run() {

                boolean isNotInterrupted = true;

                while (isNotInterrupted) {
                    try {
                        Thread.sleep(intervalInSeconds * 1000L);

                        TagReadDto read = new TagReadDto(getRandomRfidInList());
                        notifyListener(read);

                    } catch (InterruptedException e) {
                        isNotInterrupted = false;
                    }
                }

            }

            private String getRandomRfidInList() {

                Random random = new Random();

                return tagList.get(random.nextInt(tagList.size()));
            }

        };
    }

    @Override
    public void start() {
        tagReadGeneratingThread.start();
    }

    @Override
    public void stop() {
        tagReadGeneratingThread.interrupt();
    }

    private final List<String> tagList;
}
