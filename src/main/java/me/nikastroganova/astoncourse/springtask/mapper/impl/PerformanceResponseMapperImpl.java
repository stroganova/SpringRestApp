package me.nikastroganova.astoncourse.springtask.mapper.impl;

import me.nikastroganova.astoncourse.springtask.dto.ActorResponseDTO;
import me.nikastroganova.astoncourse.springtask.dto.HallDTO;
import me.nikastroganova.astoncourse.springtask.dto.PerformanceResponseDTO;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.mapper.ActorResponseMapper;
import me.nikastroganova.astoncourse.springtask.mapper.HallMapper;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PerformanceResponseMapperImpl implements PerformanceResponseMapper {

    private  ActorResponseMapper actorResponseMapper;
    private final HallMapper hallMapper;

    @Autowired
    public PerformanceResponseMapperImpl( HallMapper hallMapper) {
        this.hallMapper = hallMapper;
    }

    @Autowired
    public void setActorResponseMapper(ActorResponseMapper actorResponseMapper) {
        this.actorResponseMapper = actorResponseMapper;
    }

    @Override
    public PerformanceResponseDTO entityToDto(Performance entity) {
        List<Actor> actorEntities = entity.getActors();
        List<ActorResponseDTO> actors;
        if (actorEntities != null) {
            actors = actorEntities.stream()
                    .map(actorResponseMapper::entityToDtoWithoutList)
                    .collect(Collectors.toList());
        }
        else {
            actors = Collections.emptyList();
        }

        HallDTO hall = hallMapper.entityToDto(entity.getHall());

        return PerformanceResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .hall(hall)
                .actors(actors)
                .build();
    }

    @Override
    public PerformanceResponseDTO entityToDtoWithoutList(Performance entity) {
        HallDTO hall = hallMapper.entityToDto(entity.getHall());
        return PerformanceResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .hall(hall)
                .actors(Collections.EMPTY_LIST)
                .build();
    }

}
