package me.nikastroganova.astoncourse.springtask.mapper;

import me.nikastroganova.astoncourse.springtask.dto.HallDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;

public interface HallMapper {
    HallDTO entityToDto(Hall hall);
    Hall dtoToEntity(HallDTO hallDTO);
}
