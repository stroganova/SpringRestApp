package me.nikastroganova.astoncourse.springtask.mapper.impl;

import me.nikastroganova.astoncourse.springtask.DTO.ActorResponseDTO;
import me.nikastroganova.astoncourse.springtask.DTO.PerformanceResponseDTO;
import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.mapper.ActorResponseMapper;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActorResponseMapperImpl implements ActorResponseMapper {

    private PerformanceResponseMapper performanceResponseMapper;

    @Autowired
    public void setPerformanceResponseMapper(PerformanceResponseMapper performanceResponseMapper) {
        this.performanceResponseMapper = performanceResponseMapper;
    }

    @Override
    public ActorResponseDTO entityToDto(Actor entity) {
        List<Performance> performanceEntities = entity.getPerformances();
        List<PerformanceResponseDTO> performances;
        if (performanceEntities != null) {
            performances = entity.getPerformances().stream()
                    .map(performanceResponseMapper::entityToDtoWithoutList)
                    .collect(Collectors.toList());
        }
        else {
            performances = Collections.emptyList();
        }

        return ActorResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstname())
                .lastName(entity.getLastname())
                .phoneNumber(entity.getPhoneNumber())
                .performances(performances)
                .build();
    }

    @Override
    public ActorResponseDTO entityToDtoWithoutList(Actor entity) {
        return ActorResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstname())
                .lastName(entity.getLastname())
                .phoneNumber(entity.getPhoneNumber())
                .performances(Collections.emptyList())
                .build();
    }

}
