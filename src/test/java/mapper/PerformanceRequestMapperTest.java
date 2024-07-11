package mapper;

import me.nikastroganova.astoncourse.springtask.DTO.PerformanceRequestDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceRequestMapper;
import me.nikastroganova.astoncourse.springtask.mapper.impl.PerformanceRequestMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PerformanceRequestMapperTest {

    private PerformanceRequestMapper performanceRequestMapper;

    @Test
    void correctMappingDtoToEntity() {
        PerformanceRequestDTO performanceRequestDTO = new PerformanceRequestDTO(1, "name", "description", 1);
        Hall hall = new Hall(1, "hall1", "address1", "45454545");
        Performance performance = new Performance(1, "name", "description", hall);

        performanceRequestMapper = new PerformanceRequestMapperImpl();

        Performance performanceEntity = performanceRequestMapper.dtoToEntity(performanceRequestDTO, hall);

        Assertions.assertEquals(performanceRequestDTO.getId(), performanceEntity.getId());
        Assertions.assertEquals(performanceRequestDTO.getName(), performanceEntity.getName());
        Assertions.assertEquals(performanceRequestDTO.getDescription(), performanceEntity.getDescription());
        Assertions.assertEquals(hall, performanceEntity.getHall());
    }
}
