package me.nikastroganova.astoncourse.springtask.mapper;

import me.nikastroganova.astoncourse.springtask.DTO.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.entity.Actor;

public interface ActorRequestMapper {
    Actor dtoToEntity(ActorRequestDTO actor);
}
