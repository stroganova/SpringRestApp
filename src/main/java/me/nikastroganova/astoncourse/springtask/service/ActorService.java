package me.nikastroganova.astoncourse.springtask.service;

import me.nikastroganova.astoncourse.springtask.DTO.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.DTO.ActorResponseDTO;

public interface ActorService extends Service<ActorResponseDTO, ActorRequestDTO> {

    boolean addPerformance(int actorId, int performanceId);
    boolean removePerformance(int actorId, int performanceId);
    boolean actorExists(int id);
}
