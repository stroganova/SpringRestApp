package me.nikastroganova.astoncourse.springtask.mapper.impl;

import me.nikastroganova.astoncourse.springtask.dto.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.mapper.ActorRequestMapper;
import org.springframework.stereotype.Component;


@Component
public class ActorRequestMapperImpl implements ActorRequestMapper {

    @Override
    public Actor dtoToEntity(ActorRequestDTO dto) {
        return new Actor(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhoneNumber()
        );
    }
}
