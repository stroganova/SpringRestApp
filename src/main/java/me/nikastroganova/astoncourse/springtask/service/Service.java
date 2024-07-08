package me.nikastroganova.astoncourse.springtask.service;

import java.util.List;
import java.util.Optional;

public interface Service<K, F> {
    List<K> findAll();
    Optional<K> findById(int id);
    K save(F requestDto);
    void delete(int id);
    K update(F requestDto);
}
