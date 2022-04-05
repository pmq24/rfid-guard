package com.github.pmq24.rfid_guard.database;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.pmq24.rfid_guard.data.Tag;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeededDatabase extends Database {

    public SeededDatabase() {
        super();

        String fileContent = getSeedCsvFileContent();

        List<Tag> tagList = mapCsvStringToTagRecordList(fileContent);

        tagTable.insert(tagList);

    }



    private List<Tag> mapCsvStringToTagRecordList(String fileContent) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        List<Tag> tagList = new ArrayList<>();

        MappingIterator<Tag> tagsIterator;

        try {
            tagsIterator = mapper.readerFor(Tag.class).with(schema).readValues(fileContent);
            while (tagsIterator.hasNext()) {
                tagList.add(tagsIterator.nextValue());
            }
        } catch (IOException e) {
            System.out.println("ERROR: an error has occurred");
            e.printStackTrace();
            return tagList;
        }

        return tagList;
    }

    private String getSeedCsvFileContent() {
        URL url = getClass().getClassLoader().getResource("seed.csv");
        String path = Objects.requireNonNull(url).getPath();

        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("ERROR: File `seed.csv` not found");
            fileContent = "";
        }

        return fileContent;
    }

}
