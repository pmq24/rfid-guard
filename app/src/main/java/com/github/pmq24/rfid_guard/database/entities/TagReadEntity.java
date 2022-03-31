package com.github.pmq24.rfid_guard.database.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class TagReadEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String rfid;

    @Column(nullable = false)
    private LocalDateTime time;

}
