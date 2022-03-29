package com.github.pmq24.rfid_guard.database.repos;

import com.github.pmq24.rfid_guard.database.Hibernate;
import com.github.pmq24.rfid_guard.database.entities.TagReadEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TagReadRepo {

    public TagReadRepo(Hibernate hibernate) {
        this.hibernate = hibernate;
    }

    public void create(TagReadEntity tagReadEntity) {
        session = hibernate.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(tagReadEntity);

        transaction.commit();
        session.close();
    }

    public List<TagReadEntity> readAll() {
        session = hibernate.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TagReadEntity> criteria = builder.createQuery(TagReadEntity.class);
        criteria.from(TagReadEntity.class);
        List<TagReadEntity> data = session.createQuery(criteria).getResultList();

        session.close();

        return data;
    }

    private final Hibernate hibernate;


    private Session session;

}
