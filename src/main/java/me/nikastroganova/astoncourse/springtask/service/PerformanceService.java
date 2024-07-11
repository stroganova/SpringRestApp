package me.nikastroganova.astoncourse.springtask.service;

import me.nikastroganova.astoncourse.springtask.DTO.PerformanceRequestDTO;
import me.nikastroganova.astoncourse.springtask.DTO.PerformanceResponseDTO;


public interface PerformanceService extends Service<PerformanceResponseDTO, PerformanceRequestDTO> {

    boolean addActor(int performanceId, int actorId);
    boolean removeActor(int performanceId, int actorId);
    boolean performanceExists(int id);
}
