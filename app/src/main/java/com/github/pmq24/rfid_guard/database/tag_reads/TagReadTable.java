package com.github.pmq24.rfid_guard.database.tag_reads;

import com.github.pmq24.rfid_guard.database.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TagReadTable {

    public interface InsertedListener {
        void onInserted(TagReadRecord tagReadRecord);
    }

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

    public void setInsertedListener(InsertedListener listener) {
        insertedListener = listener;
    }

    private void notifyInsertedListener(TagReadRecord record) {
        insertedListener.onInserted(record);
    }

    private final HibernateManager hibernateManager;

    private InsertedListener insertedListener;
}