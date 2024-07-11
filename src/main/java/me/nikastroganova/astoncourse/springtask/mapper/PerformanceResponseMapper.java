package me.nikastroganova.astoncourse.springtask.mapper;

import me.nikastroganova.astoncourse.springtask.DTO.PerformanceResponseDTO;
import me.nikastroganova.astoncourse.springtask.entity.Performance;

public interface PerformanceResponseMapper  {
    PerformanceResponseDTO entityToDto(Performance performance);
    PerformanceResponseDTO entityToDtoWithoutList(Performance performance);
}
