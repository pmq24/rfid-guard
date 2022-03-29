
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.entities.TagReadEntity;
import com.github.pmq24.rfid_guard.reading.MockedTagReader;
import com.github.pmq24.rfid_guard.reading.TagReader;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Database db = new Database();

        Session session = db.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        TagReadEntity tagReadEntity = new TagReadEntity();
        tagReadEntity.setRfid("0000-0000-0000-0000");
        tagReadEntity.setTime(LocalDateTime.now());

        session.save(tagReadEntity);

        transaction.commit();
        session.close();

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
