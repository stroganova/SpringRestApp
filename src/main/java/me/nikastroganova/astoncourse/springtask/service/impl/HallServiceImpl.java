package me.nikastroganova.astoncourse.springtask.service.impl;

import me.nikastroganova.astoncourse.springtask.dto.HallDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.mapper.HallMapper;
import me.nikastroganova.astoncourse.springtask.repository.HallRepository;
import me.nikastroganova.astoncourse.springtask.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final HallMapper hallMapper;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository, HallMapper hallMapper) {
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
    }

    @Override
    @Transactional
    public List<HallDTO> findAll() {
        List<HallDTO> halls = hallRepository.findAll().stream()
                .map(hallMapper::entityToDto)
                .collect(toList());
        return halls;
    }

    @Override
    @Transactional
    public Optional<HallDTO> findById(int id) {
        Optional<Hall> hall = hallRepository.findById(id);
        if (hall.isPresent()) {
            return Optional.of(hallMapper.entityToDto(hall.get()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public HallDTO save(HallDTO hall) {
        Hall hallEntity = hallMapper.dtoToEntity(hall);
        hallEntity.setId(null);
        hallEntity = hallRepository.save(hallEntity);
        return hallMapper.entityToDto(hallEntity);
    }

    @Override
    @Transactional
    public HallDTO update(HallDTO hall) {
        Hall hallEntity = hallMapper.dtoToEntity(hall);
        hallEntity = hallRepository.update(hallEntity);
        return hallMapper.entityToDto(hallEntity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        hallRepository.delete(id);
    }

    @Override
    @Transactional
    public boolean hallExists(int id) {
        return hallRepository.existsById(id);
    }
}
