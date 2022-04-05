package com.github.pmq24.rfid_guard.database;

import com.github.pmq24.rfid_guard.data.TagRead;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TagReadTable {

    public interface InsertedListener {
        void onInserted(TagRead tagRead);
    }

    public TagReadTable(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    public void insert(TagRead tagRead) {
        Session session = hibernateManager.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(tagRead);

        transaction.commit();
        session.close();

        notifyInsertedListener(tagRead);
    }

    public List<TagRead> selectAll() {
        Session session = hibernateManager.openSession();

        CriteriaQuery<TagRead> criteria = session.getCriteriaBuilder().createQuery(TagRead.class);

        criteria.from(TagRead.class);
        List<TagRead> data = session.createQuery(criteria).getResultList();

        session.close();

        return data;
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