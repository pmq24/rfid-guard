package com.github.pmq24.rfid_guard.database.tables;

import com.github.pmq24.rfid_guard.database.HibernateManager;
import com.github.pmq24.rfid_guard.database.records.TagReadRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TagReadTable {

    public TagReadTable(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    public void insert(TagReadRecord tagReadRecord) {
        session = hibernateManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(tagReadRecord);

        transaction.commit();
        session.close();
    }

    public List<TagReadRecord> selectAll() {
        session = hibernateManager.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TagReadRecord> criteria = builder.createQuery(TagReadRecord.class);
        criteria.from(TagReadRecord.class);
        List<TagReadRecord> data = session.createQuery(criteria).getResultList();

        session.close();

        return data;
    }

    private final HibernateManager hibernateManager;


    private Session session;

}
