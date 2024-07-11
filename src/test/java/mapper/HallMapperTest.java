package mapper;

import me.nikastroganova.astoncourse.springtask.DTO.HallDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.mapper.HallMapper;
import me.nikastroganova.astoncourse.springtask.mapper.impl.HallMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HallMapperTest {

    private HallMapper hallMapper;

    @BeforeEach
    void init() {
        hallMapper = new HallMapperImpl();
    }

    @Test
    void correctMappingEntityToDto() {
        Hall hall = new Hall(1, "name", "address", "12345678");
        HallDTO hallDTO = new HallDTO(1,"name", "address", "12345678");

        HallDTO actualHallDTO = hallMapper.entityToDto(hall);

        Assertions.assertEquals(hallDTO.getId(), actualHallDTO.getId());
        Assertions.assertEquals(hallDTO.getName(), actualHallDTO.getName());
        Assertions.assertEquals(hallDTO.getAddress(), actualHallDTO.getAddress());
        Assertions.assertEquals(hallDTO.getPhoneNumber(), actualHallDTO.getPhoneNumber());

    }

    @Test
    void correctMappingDtoToEntity() {
        HallDTO hallDTO = new HallDTO(1, "name", "address", "12345678");
        Hall hall = new Hall(1, "name", "address", "12345678");

        Hall actualHall = hallMapper.dtoToEntity(hallDTO);

        Assertions.assertEquals(hallDTO.getId(), actualHall.getId());
        Assertions.assertEquals(hallDTO.getName(), actualHall.getName());
        Assertions.assertEquals(hallDTO.getAddress(), actualHall.getAddress());
        Assertions.assertEquals(hallDTO.getPhoneNumber(), actualHall.getPhoneNumber());
    }
}
