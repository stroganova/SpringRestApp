package me.nikastroganova.astoncourse.springtask.service;

import me.nikastroganova.astoncourse.springtask.dto.HallDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;

public interface HallService extends Service<HallDTO, HallDTO> {
    boolean hallExists(int id);
}
