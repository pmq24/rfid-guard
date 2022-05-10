package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.Tag;
import com.github.pmq24.rfid_guard.data.TagRead;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class PredefinedTagReader extends TagReader {

    private final Thread tagReadGeneratingThread;

    public PredefinedTagReader(List<Tag> tagList) {
        super();

        tagReadGeneratingThread = new Thread() {

            @Override
            public void run() {

                boolean isNotInterrupted = true;

                while (isNotInterrupted) {
                    try {
                        Thread.sleep( 2000);

                        TagRead tagRead = TagRead.builder()
                                .tagRfid(getRandomTagInList().getRfid())
                                .time(LocalDateTime.now())
                                .build();

                        notifyListener(tagRead);

                    } catch (InterruptedException e) {
                        isNotInterrupted = false;
                    }
                }

            }

            private Tag getRandomTagInList() {

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
