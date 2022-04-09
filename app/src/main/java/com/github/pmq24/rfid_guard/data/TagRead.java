package com.github.pmq24.rfid_guard.data;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
public class TagRead {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull String tagRfid;
    @NotNull LocalDateTime time;

    @ManyToOne
    @JoinColumn(name="rfid")
    Tag tag;

}
