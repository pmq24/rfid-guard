package com.github.pmq24.rfid_guard.database;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Hibernate {

    public Hibernate() {
        registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        MetadataSources sources = new MetadataSources(registry);
        Metadata metadata = sources.getMetadataBuilder().build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public void shutDown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Getter private final StandardServiceRegistry registry;
    @Getter private final SessionFactory sessionFactory;

}
