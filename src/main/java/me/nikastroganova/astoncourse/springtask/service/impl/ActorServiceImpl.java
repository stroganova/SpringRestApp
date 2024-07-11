package me.nikastroganova.astoncourse.springtask.service.impl;

import me.nikastroganova.astoncourse.springtask.DTO.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.DTO.ActorResponseDTO;
import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.mapper.ActorRequestMapper;
import me.nikastroganova.astoncourse.springtask.mapper.ActorResponseMapper;
import me.nikastroganova.astoncourse.springtask.repository.ActorRepository;
import me.nikastroganova.astoncourse.springtask.repository.PerformanceRepository;
import me.nikastroganova.astoncourse.springtask.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorRequestMapper actorRequestMapper;
    private final ActorResponseMapper actorResponseMapper;
    private final PerformanceRepository performanceRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, ActorRequestMapper actorRequestMapper, ActorResponseMapper actorResponseMapper, PerformanceRepository performanceRepository) {
        this.actorRepository = actorRepository;
        this.actorRequestMapper = actorRequestMapper;
        this.actorResponseMapper = actorResponseMapper;
        this.performanceRepository = performanceRepository;
    }

    @Override
    @Transactional
    public List<ActorResponseDTO> findAll() {
        List<ActorResponseDTO> halls = actorRepository.findAll().stream()
                .map(actorResponseMapper::entityToDto)
                .collect(toList());
        return halls;
    }

    @Override
    @Transactional
    public Optional<ActorResponseDTO> findById(int id) {
        Optional<Actor> actorEntity = actorRepository.findById(id);
        if (actorEntity.isPresent()) {
            return Optional.ofNullable(actorResponseMapper.entityToDto(actorEntity.get()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public ActorResponseDTO save(ActorRequestDTO actor) {
        Actor actorEntity = actorRequestMapper.dtoToEntity(actor);
        actorEntity = actorRepository.save(actorEntity);
        return actorResponseMapper.entityToDto(actorEntity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        actorRepository.delete(id);
    }

    @Override
    @Transactional
    public ActorResponseDTO update(ActorRequestDTO actor) {
        Actor actorEntity = actorRequestMapper.dtoToEntity(actor);
        actorEntity = actorRepository.update(actorEntity);
        return actorResponseMapper.entityToDto(actorEntity);
    }

    @Override
    @Transactional
    public boolean addPerformance(int actorId, int performanceId) {
        Optional<Actor> actorEntity = actorRepository.findById(actorId);
        Optional<Performance> performanceEntity = performanceRepository.findById(performanceId);
        if (actorEntity.isPresent() && performanceEntity.isPresent()) {
            return actorRepository.addPerformanceToActor(actorEntity.get(), performanceEntity.get());
        }
        return false;
    }

    @Override
    @Transactional
    public boolean removePerformance(int actorId, int performanceId) {
        Optional<Actor> actorEntity = actorRepository.findById(actorId);
        Optional<Performance> performanceEntity = performanceRepository.findById(performanceId);
        if (actorEntity.isPresent() && performanceEntity.isPresent()) {
            return actorRepository.removePerformanceFromActor(actorEntity.get(), performanceEntity.get());
        }
        return false;
    }

    @Override
    @Transactional
    public boolean actorExists(int id) {
        return actorRepository.existsById(id);
    }
}
