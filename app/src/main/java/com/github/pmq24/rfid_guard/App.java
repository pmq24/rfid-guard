
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.alarming.Alarm;
import com.github.pmq24.rfid_guard.alarming.ConsoleAlarm;
import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.SeededDatabase;
import com.github.pmq24.rfid_guard.database.tag_reads.TagReadRecord;
import com.github.pmq24.rfid_guard.database.tags.TagRecord;
import com.github.pmq24.rfid_guard.reading.PredefinedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Database database = new SeededDatabase();

        List<String> tagList = new ArrayList<>();
        database.getTagTable().selectAll().forEach(tagReadDto -> tagList.add(tagReadDto.getRfid()));

        TagReader tagReader = new PredefinedTagReader(tagList, 2);
        Alarm alarm = new ConsoleAlarm();

        database.getTagTable().selectAll().forEach(System.out::println);

        tagReader.setTagReadListener(tagReadDto -> {
            TagReadRecord tagReadRecord = new TagReadRecord();
            tagReadRecord.setTagRfid(tagReadDto.getRfid());
            tagReadRecord.setTime(tagReadDto.getTime());

            database.getTagReadTable().insert(tagReadRecord);
        });

        database.getTagReadTable().setInsertedListener(tagReadRecord -> {
            TagRecord tagRecord = database.getTagTable().selectByRfid(tagReadRecord.getTagRfid());
            if (!tagRecord.getIsPurchased()) {
                alarm.alarm(tagReadRecord);
            }
        });

        tagReader.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tagReader.stop();

        database.getTagReadTable().selectAll().forEach(System.out::println);

    }
}
