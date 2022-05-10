
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.alarming.Alarm;
import com.github.pmq24.rfid_guard.alarming.SoundAlarm;
import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.SeededDatabase;
import com.github.pmq24.rfid_guard.gui.MainWindow;
import com.github.pmq24.rfid_guard.reading.PredefinedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;
import com.impinj.octane.OctaneSdkException;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URI;

public class App {

    public static void main(String[] args) throws OctaneSdkException {

        Database database = new SeededDatabase();
        TagReader tagReader = new PredefinedTagReader(database.getTagTable().selectAll());

        URI uri = URI.create("http://192.168.1.12:3010");

        Socket socket = IO.socket(uri);

        socket.connect();

        Ack ack = args1 -> {
            for (Object o : args1) {
                System.out.println(o);
            }
        };

        MainWindow mainWindow = new MainWindow();
        Alarm alarm = new SoundAlarm();

        tagReader.setTagReadListener(tagRead -> {
            // System.out.println("TAG READER - new tag read: " + tagRead);
            // database.getTagReadTable().insert(tagRead);

            socket.emit("detect", "{\"epc\":\"asd\"}", ack);
        });

        database.getTagReadTable().setInsertedListener(tagRead -> {
            System.out.println("DATABASE - inserted new tag read: " + tagRead);

            final String status = tagRead.getTag() == null ? "Unknown" : (tagRead.getTag().getIsPurchased() ? "Purchased" : "Not Purchased");

            if (tagRead.getTag() == null || !tagRead.getTag().getIsPurchased()) {
                alarm.alarm(tagRead);
            }

            mainWindow.addTagReadRow(tagRead, status);
        });

        mainWindow.setCloseListener(() -> {
            tagReader.stop();
            database.destroy();
        });

        tagReader.start();
        mainWindow.showGui();

    }
}
