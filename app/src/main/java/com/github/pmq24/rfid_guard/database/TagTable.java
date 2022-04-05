package com.github.pmq24.rfid_guard.database;

import com.github.pmq24.rfid_guard.data.Tag;
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

    public void insert(Tag tag) {
        Session session = hibernateManager.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(tag);

        transaction.commit();
        session.close();
    }

    public void insert(List<Tag> tags) {
        Session session = hibernateManager.openSession();
        Transaction transaction = session.beginTransaction();

        tags.forEach(session::save);

        transaction.commit();
        session.close();
    }

    public List<Tag> selectAll() {
        Session session = hibernateManager.openSession();

        CriteriaQuery<Tag> criteria = session.getCriteriaBuilder().createQuery(Tag.class);

        criteria.from(Tag.class);
        List<Tag> data = session.createQuery(criteria).getResultList();

        session.close();

        return data;
    }

    public Tag selectByRfid(String rfid) {
        Session session = hibernateManager.openSession();

        Query query = session.createQuery("from Tag where rfid = :rfid");
        query.setParameter("rfid", rfid);

        return (Tag) query.getResultList().get(0);
    }

}
