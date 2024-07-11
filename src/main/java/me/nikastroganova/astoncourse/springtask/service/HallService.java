package me.nikastroganova.astoncourse.springtask.service;

import me.nikastroganova.astoncourse.springtask.DTO.HallDTO;

public interface HallService extends Service<HallDTO, HallDTO> {
    boolean hallExists(int id);
}
