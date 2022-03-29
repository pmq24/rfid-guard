package com.github.pmq24.rfid_guard.reading;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagReadDto {

    public TagReadDto(String rfid, LocalDateTime time) {
        this.rfid = rfid;
        this.time = time;
    }

    public TagReadDto(String rfid) {
        this.rfid = rfid;
        this.time = LocalDateTime.now();
    }

    private String rfid;
    private LocalDateTime time;

}
