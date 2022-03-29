
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.reading.MockedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        TagReader reader = new MockedTagReader();
        reader.addTagReadListener(read -> {
            String log = "RFID: " + read.getRfid() + ", time: " + read.getTime();
            System.out.println(log);
        });

        reader.start();

        System.out.println("Press Enter to exit.");
        Scanner s = new Scanner(System.in);
        s.nextLine();

        reader.stop();

    }
}
