package com.github.pmq24.rfid_guard.database;

import com.github.pmq24.rfid_guard.database.tag_reads.TagReadTable;
import com.github.pmq24.rfid_guard.database.tags.TagTable;
import lombok.Getter;

public class Database {

    public Database() {
        hibernateManager = new HibernateManager();
        tagReadTable = new TagReadTable(hibernateManager);
        tagTable = new TagTable(hibernateManager);
    }

    public void destroy() {
        hibernateManager.shutDown();
    }

    protected final HibernateManager hibernateManager;
    @Getter protected final TagReadTable tagReadTable;
    @Getter protected final TagTable tagTable;


}
