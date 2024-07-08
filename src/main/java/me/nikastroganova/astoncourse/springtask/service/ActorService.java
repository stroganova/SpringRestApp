package me.nikastroganova.astoncourse.springtask.service;

import me.nikastroganova.astoncourse.springtask.dto.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.dto.ActorResponseDTO;

public interface ActorService extends Service<ActorResponseDTO, ActorRequestDTO> {

    boolean addPerformance(int actorId, int performanceId);
    boolean removePerformance(int actorId, int performanceId);
    boolean actorExists(int id);
}
