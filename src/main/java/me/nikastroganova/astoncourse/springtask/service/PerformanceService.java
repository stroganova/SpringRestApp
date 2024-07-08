package me.nikastroganova.astoncourse.springtask.service;

import me.nikastroganova.astoncourse.springtask.dto.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.dto.PerformanceRequestDTO;
import me.nikastroganova.astoncourse.springtask.dto.PerformanceResponseDTO;


public interface PerformanceService extends Service<PerformanceResponseDTO, PerformanceRequestDTO> {

    boolean addActor(int performanceId, int actorId);
    boolean removeActor(int performanceId, int actorId);
    boolean performanceExists(int id);
}
