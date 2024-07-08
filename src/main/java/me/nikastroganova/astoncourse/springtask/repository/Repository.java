package me.nikastroganova.astoncourse.springtask.repository;

import me.nikastroganova.astoncourse.springtask.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface Repository<K> {

    List<K> findAll();
    Optional<K> findById(int id);
    K save(K entity);
    void delete(int id);
    K update(K entity);
    boolean existsById(int id);

}
