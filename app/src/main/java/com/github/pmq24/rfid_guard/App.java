
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.alarming.Alarm;
import com.github.pmq24.rfid_guard.alarming.SoundAlarm;
import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.SeededDatabase;
import com.github.pmq24.rfid_guard.gui.MainWindow;
import com.github.pmq24.rfid_guard.reading.PredefinedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;
import com.impinj.octane.OctaneSdkException;

public class App {

    public static void main(String[] args) throws OctaneSdkException {

        Database database = new SeededDatabase();
        TagReader tagReader = new PredefinedTagReader(database.getTagTable().selectAll());

        MainWindow mainWindow = new MainWindow();
        Alarm alarm = new SoundAlarm();

        tagReader.setTagReadListener(tagRead -> {
            System.out.println("TAG READER - new tag read: " + tagRead);
            database.getTagReadTable().insert(tagRead);
        });

        database.getTagReadTable().setInsertedListener(tagRead -> {
            System.out.println("DATABASE - inserted new tag read: " + tagRead);

            final String status = tagRead.getTag() == null ? "Unknown" : (tagRead.getTag().getIsPurchased() ? "Purchased" : "Not Purchased");

            final String Notes = tagRead.getTag().getNotes();

            if (tagRead.getTag() == null || !tagRead.getTag().getIsPurchased()) {
                alarm.alarm(tagRead);
            }

            mainWindow.addTagReadRow(tagRead, status, Notes);
        });

        mainWindow.setCloseListener(() -> {
            tagReader.stop();
            database.destroy();
        });

        tagReader.start();
        mainWindow.showGui();

    }
}
