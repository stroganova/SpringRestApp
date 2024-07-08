package me.nikastroganova.astoncourse.springtask.repository.impl;

import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.repository.PerformanceRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PerformanceRepositoryImpl implements PerformanceRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PerformanceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Performance> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Performance> query = session.createQuery("from Performance ", Performance.class);
        List<Performance> performances = query.getResultList();
        return performances;
    }

    @Override
    public Optional<Performance> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Performance performance = session.get(Performance.class, id);
        return Optional.ofNullable(performance);
    }

    @Override
    public Performance save(Performance entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        MutationQuery query = session.createMutationQuery("delete from Performance where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Performance update(Performance entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
        return entity;
    }

    @Override
    public boolean existsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Performance performance = session.get(Performance.class, id);
        return performance != null;
    }

    @Override
    public boolean addActorToPerformance(Performance performance, Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        Boolean isAdded = performance.addActorToPerformance(actor);
        session.merge(performance);
        return isAdded;
    }

    @Override
    public boolean removeActorFromPerformance(Performance performance, Actor actor) {
        Session session = sessionFactory.getCurrentSession();
        Boolean isRemoved = performance.removeActorFromPerformance(actor);
        session.merge(performance);
        return isRemoved;
    }
}
