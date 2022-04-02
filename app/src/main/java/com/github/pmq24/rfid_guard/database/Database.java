package com.github.pmq24.rfid_guard.database;

import com.github.pmq24.rfid_guard.database.tag_reads.TagReadTable;
import lombok.Getter;

public class Database {

    public Database() {
        hibernateManager = new HibernateManager();
        tagReadTable = new TagReadTable(hibernateManager);
    }

    public void destroy() {
        hibernateManager.shutDown();
    }

    private final HibernateManager hibernateManager;
    @Getter private final TagReadTable tagReadTable;


}
