
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.alarming.Alarm;
import com.github.pmq24.rfid_guard.alarming.SoundAlarm;
import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.SeededDatabase;
import com.github.pmq24.rfid_guard.gui.MainWindow;
import com.github.pmq24.rfid_guard.reading.ImpinjTagReader;
import com.github.pmq24.rfid_guard.reading.PredefinedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;
import com.impinj.octane.OctaneSdkException;

public class App {

    public static void main(String[] args) {

        Database database = new SeededDatabase();
        TagReader tagReader = new PredefinedTagReader(database.getTagTable().selectAll());

        try {
            tagReader = new ImpinjTagReader("");
        } catch (OctaneSdkException e) {
            System.out.println("ERROR: CANNOT CONNECT TO DEVICE. EXITING...");
            return;
        }

        MainWindow mainWindow = new MainWindow();
        Alarm alarm = new SoundAlarm();

        tagReader.setTagReadListener(tagRead -> {
            System.out.println("TAG READER - new tag read: " + tagRead);
            database.getTagReadTable().insert(tagRead);
        });

        database.getTagReadTable().setInsertedListener(tagRead -> {
            System.out.println("DATABASE - inserted new tag read: " + tagRead);

            if (tagRead.getTag() == null || !tagRead.getTag().getIsPurchased())
                alarm.alarm(tagRead);

            mainWindow.addTagReadRow(tagRead, tagRead.getTag().getIsPurchased());
        });

        TagReader finalTagReader = tagReader;

        mainWindow.setCloseListener(() -> {
            finalTagReader.stop();
            database.destroy();
        });

        tagReader.start();
        mainWindow.showGui();

    }
}
