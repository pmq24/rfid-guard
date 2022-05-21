package com.github.pmq24.rfid_guard.database;

import com.github.pmq24.rfid_guard.data.Tag;
import com.github.pmq24.rfid_guard.data.TagRead;
import com.github.pmq24.rfid_guard.data.Tag_;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TagReadTable {

    public interface InsertedListener {
        void onInserted(TagRead tagRead);
    }

    public TagReadTable(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    public void insert(TagRead tagRead) {
        Session session = hibernateManager.openSession();

        Query query = session.createQuery("from Tag where rfid = :rfid");
        query.setParameter("rfid", tagRead.getTagRfid());
        final Tag tag = (Tag) query.getResultList().get(0);

        Transaction transaction = session.beginTransaction();

        if (tag == null) {
            System.out.println("WARN: Inserting an unregistered tag read: " + tagRead);
        } else {
            tagRead.setTag(tag);
        }

        session.save(tagRead);

        transaction.commit();
        session.close();

        notifyInsertedListener(tagRead);
    }

    public void setInsertedListener(InsertedListener listener) {
        insertedListener = listener;
    }

    private void notifyInsertedListener(TagRead record) {
        insertedListener.onInserted(record);
    }

    private final HibernateManager hibernateManager;

    private InsertedListener insertedListener;
}