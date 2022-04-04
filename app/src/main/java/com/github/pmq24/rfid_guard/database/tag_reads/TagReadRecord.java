package com.github.pmq24.rfid_guard.database.tag_reads;

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
    private String tagRfid;

    @Column(nullable = false)
    private LocalDateTime time;

    public interface PostInsertListener {
        void onPostInsert(TagReadRecord tagReadRecord);
    }

}
