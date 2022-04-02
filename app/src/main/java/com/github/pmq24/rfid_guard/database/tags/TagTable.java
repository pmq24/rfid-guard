package com.github.pmq24.rfid_guard.database.tags;

import com.github.pmq24.rfid_guard.database.HibernateManager;
import com.github.pmq24.rfid_guard.database.tag_reads.TagReadRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TagTable {

    private final HibernateManager hibernateManager;

    public TagTable(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    public void insert(TagRecord tagRecord) {
        Session session = hibernateManager.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(tagRecord);

        transaction.commit();
        session.close();

    }

    public List<TagRecord> selectAll() {
        Session session = hibernateManager.openSession();

        CriteriaQuery<TagRecord> criteria = session.getCriteriaBuilder().createQuery(TagRecord.class);

        criteria.from(TagRecord.class);
        List<TagRecord> data = session.createQuery(criteria).getResultList();

        session.close();

        return data;
    }

}
