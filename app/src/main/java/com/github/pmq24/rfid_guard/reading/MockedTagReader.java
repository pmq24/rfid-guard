package com.github.pmq24.rfid_guard.reading;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MockedTagReader extends TagReader {

    public MockedTagReader() {
        super();
        tagReadGeneratingThread = new Thread() {

            @Override
            public void run() {

                boolean isNotInterrupted = true;

                while (isNotInterrupted) {
                    try {
                        Thread.sleep(THREE_SECS);

                        TagReadDto read = new TagReadDto(generateRandomRfid());
                        notifyListener(read);

                    } catch (InterruptedException e) {
                        isNotInterrupted = false;
                    }
                }

            }

            private String generateRandomRfid() {

                final int RFID_LENGTH = 16;

                final Set<Integer> POSITIONS_TO_PUT_DASH = new HashSet<>();
                POSITIONS_TO_PUT_DASH.add(3);
                POSITIONS_TO_PUT_DASH.add(7);
                POSITIONS_TO_PUT_DASH.add(11);

                StringBuilder rfidStringBuilder = new StringBuilder();

                for (int i = 0; i < RFID_LENGTH; i++) {
                    rfidStringBuilder.append(getRandomHexChar());
                    if (POSITIONS_TO_PUT_DASH.contains(i))
                        rfidStringBuilder.append("-");
                }

                return rfidStringBuilder.toString();
            }

            private char getRandomHexChar() {
                final String HEX_CHARS = "0123456789ABCDEF";
                Random random = new Random();
                return HEX_CHARS.charAt(random.nextInt(HEX_CHARS.length()));
            }

            private static final long THREE_SECS = 3000;

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

    private final Thread tagReadGeneratingThread;

}
