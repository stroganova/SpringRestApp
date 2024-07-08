package me.nikastroganova.astoncourse.springtask.repository;

import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Performance;

public interface PerformanceRepository extends Repository<Performance> {
    boolean addActorToPerformance(Performance performance, Actor actor);
    boolean removeActorFromPerformance(Performance performance, Actor actor);
}
