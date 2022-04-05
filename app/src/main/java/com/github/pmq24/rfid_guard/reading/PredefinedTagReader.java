package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class PredefinedTagReader extends TagReader {

    private final Thread tagReadGeneratingThread;

    public PredefinedTagReader(List<String> tagList, int intervalInSeconds) {
        super();

        tagReadGeneratingThread = new Thread() {

            @Override
            public void run() {

                boolean isNotInterrupted = true;

                while (isNotInterrupted) {
                    try {
                        Thread.sleep(intervalInSeconds * 1000L);

                        TagRead tagRead = TagRead.builder()
                                .tagRfid(getRandomRfidInList())
                                .time(LocalDateTime.now())
                                .build();

                        notifyListener(tagRead);

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

}
