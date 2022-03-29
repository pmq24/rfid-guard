package com.github.pmq24.rfid_guard.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Database {

    public Database() {
        setUpSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private void setUpSessionFactory() {
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            MetadataSources sources = new MetadataSources(registry);

            Metadata metadata = sources.getMetadataBuilder().build();


            sessionFactory = metadata.getSessionFactoryBuilder().build();


        } catch (Exception e) {
            e.printStackTrace();
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
    }

    public void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;

}
