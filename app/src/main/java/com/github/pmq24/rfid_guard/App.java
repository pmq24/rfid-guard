
package com.github.pmq24.rfid_guard;

import com.github.pmq24.rfid_guard.database.Database;
import com.github.pmq24.rfid_guard.database.SeededDatabase;

public class App {

    public static void main(String[] args) {

        Database db = new SeededDatabase();

    }
}
