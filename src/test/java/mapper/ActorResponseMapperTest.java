package mapper;

import me.nikastroganova.astoncourse.springtask.DTO.ActorResponseDTO;
import me.nikastroganova.astoncourse.springtask.DTO.HallDTO;
import me.nikastroganova.astoncourse.springtask.DTO.PerformanceResponseDTO;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceResponseMapper;
import me.nikastroganova.astoncourse.springtask.mapper.impl.ActorResponseMapperImpl;
import me.nikastroganova.astoncourse.springtask.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ActorResponseMapperTest {

    @Mock
    private PerformanceResponseMapper performanceResponseMapper;

    @InjectMocks
    private ActorResponseMapperImpl actorResponseMapper;


    @Test
    void correctMappingEntityToDto() {
        Hall hall = new Hall(1, "hall1", "address1", "45454545");
        Performance performance = new Performance(1, "name", "description", hall);
        List<Performance> performances = new ArrayList<>();
        performances.add(performance);
        Actor actor = new Actor(1, "name", "lastname", "3437434324324");
        actor.setPerformances(performances);

        HallDTO hallDTO = new HallDTO(1, "hall1", "address1", "45454545");
        PerformanceResponseDTO performanceResponseDTO = new PerformanceResponseDTO(1, "name", "description", hallDTO, Collections.EMPTY_LIST);
        List<PerformanceResponseDTO> ListOfPerformanceDTO = new ArrayList<>();
        ListOfPerformanceDTO.add(performanceResponseDTO);
        ActorResponseDTO expectedActorResponseDTO = new ActorResponseDTO(1, "name", "lastname", "3437434324324", ListOfPerformanceDTO);

        Mockito.when(performanceResponseMapper.entityToDtoWithoutList(performance)).thenReturn(performanceResponseDTO);

        ActorResponseDTO actualActorResponseDTO = actorResponseMapper.entityToDto(actor);

        Assertions.assertEquals(expectedActorResponseDTO, actualActorResponseDTO);

        Mockito.verify(performanceResponseMapper).entityToDtoWithoutList(performance);

    }

    @Test
    void mappingWithPerformancesListNull() {
        Actor actor = new Actor(1, "name", "lastname", "3437434324324");
        ActorResponseDTO expectedActorResponseDTO = new ActorResponseDTO(1, "name", "lastname", "3437434324324", Collections.EMPTY_LIST);

        ActorResponseDTO actualActorResponseDTO = actorResponseMapper.entityToDto(actor);

        Assertions.assertEquals(expectedActorResponseDTO, actualActorResponseDTO);
        Mockito.verifyNoInteractions(performanceResponseMapper);
    }
}
