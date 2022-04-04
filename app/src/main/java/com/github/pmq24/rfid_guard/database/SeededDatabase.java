package com.github.pmq24.rfid_guard.database;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class SeededDatabase extends Database {

    public SeededDatabase() {
        super();

        String fileContent = getSeedCsvFileContent();

        System.out.println(fileContent);

    }

    private String getSeedCsvFileContent() {
        URL url = getClass().getClassLoader().getResource("seed.csv");
        String path = Objects.requireNonNull(url).getPath();

        String fileContent = "";

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("ERROR: File `seed.csv` not found");
            fileContent = "";
        }

        return fileContent;
    }

}
