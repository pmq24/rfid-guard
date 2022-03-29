
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.entities.TagReadEntity;
import com.github.pmq24.rfid_guard.reading.MockedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;

import java.text.MessageFormat;

public class App {

    public static void main(String[] args) {

        Database db = new Database();
        TagReader reader = new MockedTagReader();

        reader.addTagReadListener(tagReadDto -> {
            String log = "RFID: " + tagReadDto.getRfid() + ", time: " + tagReadDto.getTime();
            System.out.println(log);
        });

        reader.addTagReadListener(tagReadDto ->{
            TagReadEntity tagReadEntity = new TagReadEntity();
            tagReadEntity.setRfid(tagReadDto.getRfid());
            tagReadEntity.setTime(tagReadDto.getTime());

            db.getTagReadRepo().create(tagReadEntity);
        });

        reader.start();

        System.out.println("The program will be running for 10 seconds");

        final long TEN_SECONDS = 10000;

        try {
            Thread.sleep(TEN_SECONDS);
        } catch (InterruptedException ignored) {}

        reader.stop();

        System.out.println("==============");
        System.out.println("PRINT ALL RFID");
        System.out.println("==============");

        db.getTagReadRepo().readAll().forEach(tagReadEntity -> System.out.println(
                MessageFormat.format(
                        "ID: {0}, RFID: {1}, Time: {2}",
                        tagReadEntity.getId(),
                        tagReadEntity.getRfid(),
                        tagReadEntity.getTime()
                )
        ));

        db.destroy();

    }
}
