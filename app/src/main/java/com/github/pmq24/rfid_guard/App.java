
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.tag_reads.TagReadRecord;
import com.github.pmq24.rfid_guard.reading.MockedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;
import com.github.pmq24.rfid_guard.gui.Reader_Gui;

public class App {

    public static void main(String[] args) {

        TagReader reader = new MockedTagReader();
        Database db = new Database();
        Reader_Gui gui = new Reader_Gui();
        gui.setVisible(true);

        reader.addTagReadListener(tagReadDto ->
                System.out.println(tagReadDto.getRfid() + ", " + tagReadDto.getTime().toString())
        );

        reader.addTagReadListener(tagReadDto -> {
            TagReadRecord record = new TagReadRecord();
            record.setRfid(tagReadDto.getRfid());
            record.setTime(tagReadDto.getTime());
            db.getTagReadTable().insert(record);
        });
        
        reader.addTagReadListener(readTagDto -> {
            TagReadRecord record = new TagReadRecord();
            record.setRfid(readTagDto.getRfid());
            record.setTime(readTagDto.getTime());
            gui.insert(record);
        });

        reader.start();

        final int TEN_SECONDS = 20000;
        try {
            Thread.sleep(TEN_SECONDS);
        } catch (InterruptedException ignored) {}

        reader.stop();
        db.getTagReadTable().selectAll().forEach(record -> System.out.println(record.toString()));
        
    }
}
