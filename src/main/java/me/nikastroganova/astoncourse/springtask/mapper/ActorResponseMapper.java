package me.nikastroganova.astoncourse.springtask.mapper;

import me.nikastroganova.astoncourse.springtask.DTO.ActorResponseDTO;
import me.nikastroganova.astoncourse.springtask.entity.Actor;

public interface ActorResponseMapper {
    ActorResponseDTO entityToDto(Actor actor);
    ActorResponseDTO entityToDtoWithoutList(Actor actor);
}
