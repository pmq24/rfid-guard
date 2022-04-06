package com.github.pmq24.rfid_guard.data;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {
    @Id String rfid;
    @NotNull Boolean isPurchased;
}
