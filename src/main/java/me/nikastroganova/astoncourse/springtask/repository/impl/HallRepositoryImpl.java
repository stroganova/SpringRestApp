package me.nikastroganova.astoncourse.springtask.repository.impl;

import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.repository.HallRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HallRepositoryImpl implements HallRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HallRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Hall> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Hall> query = session.createQuery("from Hall", Hall.class);
        List<Hall> halls = query.getResultList();
        return halls;
    }

    @Override
    public Optional<Hall> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Hall hall = session.get(Hall.class, id);
        return Optional.ofNullable(hall);
    }

    @Override
    public Hall save(Hall entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        MutationQuery query = session.createMutationQuery("delete from Hall where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Hall update(Hall entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
        return entity;
    }

    @Override
    public boolean existsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Hall hall = session.get(Hall.class, id);
        return hall != null;
    }
}
