package me.nikastroganova.astoncourse.springtask.repository;

import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Performance;

public interface ActorRepository extends Repository<Actor> {
    boolean addPerformanceToActor(Actor actor, Performance performance);
    boolean removePerformanceFromActor(Actor actor, Performance performance);
}
