package com.github.pmq24.rfid_guard.database.tag_reads;

import com.github.pmq24.rfid_guard.database.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TagReadTable {

    public TagReadTable(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    public void insert(TagReadRecord tagReadRecord) {
        Session session = hibernateManager.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(tagReadRecord);

        transaction.commit();
        session.close();
    }

    public List<TagReadRecord> selectAll() {
        Session session = hibernateManager.openSession();

        CriteriaQuery<TagReadRecord> criteria = session.getCriteriaBuilder().createQuery(TagReadRecord.class);

        criteria.from(TagReadRecord.class);
        List<TagReadRecord> data = session.createQuery(criteria).getResultList();

        session.close();

        return data;
    }

    public void addPostInsertListener(TagReadRecord.PostInsertListener listener) {
        postInsertListeners.add(listener);
    }

    public void removePostInsertListener(TagReadRecord.PostInsertListener listener) {
        postInsertListeners.remove(listener);
    }

    private void notifyPostInsertListeners(TagReadRecord record) {
        postInsertListeners.forEach(listener -> listener.onPostInsert(record));
    }

    private final HibernateManager hibernateManager;

    private List<TagReadRecord.PostInsertListener> postInsertListeners;
}