package com.github.pmq24.rfid_guard.database.tags;

import com.github.pmq24.rfid_guard.database.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
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

    public void insert(List<TagRecord> tagRecords) {
        Session session = hibernateManager.openSession();
        Transaction transaction = session.beginTransaction();

        tagRecords.forEach(session::save);

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

    public TagRecord selectByRfid(String rfid) {
        Session session = hibernateManager.openSession();

        Query query = session.createQuery("from TagRecord where rfid = :rfid");
        query.setParameter("rfid", rfid);

        return (TagRecord) query.getResultList().get(0);
    }

}
