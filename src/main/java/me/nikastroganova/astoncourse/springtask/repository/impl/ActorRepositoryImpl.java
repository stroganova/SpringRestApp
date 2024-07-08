package me.nikastroganova.astoncourse.springtask.repository.impl;

import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.repository.ActorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Actor> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Actor> query = session.createQuery("from Actor", Actor.class);
        List<Actor> actors = query.getResultList();
        return actors;
    }

    @Override
    public Optional<Actor> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Actor actor = session.get(Actor.class, id);
        return Optional.ofNullable(actor);
    }

    @Override
    public Actor save(Actor entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        MutationQuery query = session.createMutationQuery("delete from Actor where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Actor update(Actor entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
        return entity;
    }

    @Override
    public boolean existsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Actor actor = session.get(Actor.class, id);
        return actor != null;
    }

    @Override
    public boolean addPerformanceToActor(Actor actor, Performance performance) {
        Session session = sessionFactory.getCurrentSession();
        Boolean isAdded = actor.addPerformanceToActor(performance);
        session.merge(actor);
        return isAdded;
    }

    public boolean removePerformanceFromActor(Actor actor, Performance performance) {
        Session session = sessionFactory.getCurrentSession();
        Boolean isRemoved = actor.removePerformanceFromActor(performance);
        session.merge(actor);
        return isRemoved;
    }
}
