package com.github.pmq24.rfid_guard.database.tags;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TagRecord {

    @Id
    private String rfid;

    @Column(nullable = false)
    private Boolean isPurchased;

}
