package me.nikastroganova.astoncourse.springtask.mapper.impl;

import me.nikastroganova.astoncourse.springtask.DTO.PerformanceRequestDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class PerformanceRequestMapperImpl implements PerformanceRequestMapper {

    @Override
    public Performance dtoToEntity(PerformanceRequestDTO dto, Hall hall) {
        return new Performance(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                hall
        );
    }
}
