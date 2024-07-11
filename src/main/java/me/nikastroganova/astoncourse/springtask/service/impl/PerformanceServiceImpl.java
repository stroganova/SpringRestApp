package me.nikastroganova.astoncourse.springtask.service.impl;

import me.nikastroganova.astoncourse.springtask.DTO.PerformanceRequestDTO;
import me.nikastroganova.astoncourse.springtask.DTO.PerformanceResponseDTO;
import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.mapper.ActorRequestMapper;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceRequestMapper;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceResponseMapper;
import me.nikastroganova.astoncourse.springtask.repository.ActorRepository;
import me.nikastroganova.astoncourse.springtask.repository.HallRepository;
import me.nikastroganova.astoncourse.springtask.repository.PerformanceRepository;
import me.nikastroganova.astoncourse.springtask.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final HallRepository hallRepository;
    private final ActorRepository actorRepository;
    private final PerformanceResponseMapper performanceResponseMapper;
    private final PerformanceRequestMapper performanceRequestMapper;
    private final ActorRequestMapper actorRequestMapper;

    @Autowired
    public PerformanceServiceImpl(PerformanceRepository performanceRepository, HallRepository hallRepository, ActorRepository actorRepository, PerformanceResponseMapper performanceResponseMapper, PerformanceRequestMapper performanceRequestMapper, ActorRequestMapper actorRequestMapper) {
        this.performanceRepository = performanceRepository;
        this.hallRepository = hallRepository;
        this.actorRepository = actorRepository;
        this.performanceResponseMapper = performanceResponseMapper;
        this.performanceRequestMapper = performanceRequestMapper;
        this.actorRequestMapper = actorRequestMapper;
    }

    @Override
    @Transactional
    public List<PerformanceResponseDTO> findAll() {
        List<PerformanceResponseDTO> halls = performanceRepository.findAll().stream()
                .map(performanceResponseMapper::entityToDto)
                .collect(toList());
        return halls;
    }

    @Override
    @Transactional
    public Optional<PerformanceResponseDTO> findById(int id) {
        Optional<Performance> performanceEntity = performanceRepository.findById(id);
        if (performanceEntity.isPresent()) {
            return Optional.ofNullable(performanceResponseMapper.entityToDto(performanceEntity.get()));
        }
        return Optional.empty();
        }


    @Override
    @Transactional
    public PerformanceResponseDTO save(PerformanceRequestDTO performance) {
        Optional<Hall> hall = hallRepository.findById(performance.getHallId());
        Performance performanceEntity = performanceRequestMapper.dtoToEntity(performance, hall.get());
        performanceEntity = performanceRepository.save(performanceEntity);
        return performanceResponseMapper.entityToDto(performanceEntity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        performanceRepository.delete(id);
    }

    @Override
    @Transactional
    public PerformanceResponseDTO update(PerformanceRequestDTO performance) {
        Optional<Hall> hall = hallRepository.findById(performance.getHallId());
        Performance performanceEntity = performanceRequestMapper.dtoToEntity(performance, hall.get());
        performanceEntity = performanceRepository.update(performanceEntity);
        return performanceResponseMapper.entityToDto(performanceEntity);
    }

    @Override
    @Transactional
    public boolean addActor(int performanceId, int actorId) {
        Optional<Performance> performanceEntity = performanceRepository.findById(performanceId);
        Optional<Actor> actorEntity = actorRepository.findById(actorId);
        if (actorEntity.isPresent() && performanceEntity.isPresent()) {
            return performanceRepository.addActorToPerformance(performanceEntity.get(), actorEntity.get());
        }
        return false;
    }

    @Override
    @Transactional
    public boolean removeActor(int performanceId, int actorId) {
        Optional<Performance> performanceEntity = performanceRepository.findById(performanceId);
        Optional<Actor> actorEntity = actorRepository.findById(actorId);
        if (actorEntity.isPresent() && performanceEntity.isPresent()) {
            return performanceRepository.removeActorFromPerformance(performanceEntity.get(), actorEntity.get());
        }
        return false;
    }

    @Override
    @Transactional
    public boolean performanceExists(int id) {
        return performanceRepository.existsById(id);
    }
}
