
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.data.TagRead;
import com.github.pmq24.rfid_guard.gui.MainWindow;

import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();
        mainWindow.showGui();

        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), true);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), false);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), true);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), true);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), true);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), false);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), true);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), true);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), true);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), false);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), false);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), false);
        mainWindow.addTagReadRow(TagRead.builder().tagRfid("1").time(LocalDateTime.now()).build(), false);


    }
}
