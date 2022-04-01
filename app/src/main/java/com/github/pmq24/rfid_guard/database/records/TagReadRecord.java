package com.github.pmq24.rfid_guard.database.records;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class TagReadRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String rfid;

    @Column(nullable = false)
    private LocalDateTime time;

}
