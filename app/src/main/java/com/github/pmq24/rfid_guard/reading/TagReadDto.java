package com.github.pmq24.rfid_guard.reading;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagReadDto {

    private String rfid;
    private LocalDateTime time;

}
