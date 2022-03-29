package com.github.pmq24.rfid_guard.database;

import com.github.pmq24.rfid_guard.database.repos.TagReadRepo;
import lombok.Getter;

public class Database {

    public Database() {
        hibernate = new Hibernate();
        tagReadRepo = new TagReadRepo(hibernate);
    }

    public void destroy() {
        hibernate.shutDown();
    }

    private final Hibernate hibernate;

    @Getter private final TagReadRepo tagReadRepo;

}
