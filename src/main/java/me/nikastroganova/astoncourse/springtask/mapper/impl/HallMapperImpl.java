package me.nikastroganova.astoncourse.springtask.mapper.impl;

import me.nikastroganova.astoncourse.springtask.DTO.HallDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.mapper.HallMapper;
import org.springframework.stereotype.Component;

@Component
public class HallMapperImpl implements HallMapper {

    @Override
    public HallDTO entityToDto(Hall hall) {
        return HallDTO.builder()
                .id(hall.getId())
                .name(hall.getName())
                .address(hall.getAddress())
                .phoneNumber(hall.getPhoneNumber())
                .build();
    }

    @Override
    public Hall dtoToEntity(HallDTO hallDTO) {
        return new Hall(hallDTO.getId(), hallDTO.getName(), hallDTO.getAddress(), hallDTO.getPhoneNumber());
    }
}
