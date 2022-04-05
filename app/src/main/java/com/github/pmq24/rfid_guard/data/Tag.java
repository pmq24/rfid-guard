package com.github.pmq24.rfid_guard.data;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Tag {
    @Id String rfid;
    @NotNull Boolean isPurchased;
}
