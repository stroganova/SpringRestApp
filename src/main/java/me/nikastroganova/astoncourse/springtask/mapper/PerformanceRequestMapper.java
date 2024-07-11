package me.nikastroganova.astoncourse.springtask.mapper;

import me.nikastroganova.astoncourse.springtask.DTO.PerformanceRequestDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.entity.Performance;

public interface PerformanceRequestMapper {
    Performance dtoToEntity(PerformanceRequestDTO dto, Hall hall);
}
