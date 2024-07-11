package mapper;

import me.nikastroganova.astoncourse.springtask.DTO.*;
import me.nikastroganova.astoncourse.springtask.entity.*;
import me.nikastroganova.astoncourse.springtask.mapper.ActorResponseMapper;
import me.nikastroganova.astoncourse.springtask.mapper.HallMapper;
import me.nikastroganova.astoncourse.springtask.mapper.impl.PerformanceResponseMapperImpl;
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
public class PerformanceResponseMapperTest {

    @Mock
    private ActorResponseMapper actorResponseMapper;

    @Mock
    private HallMapper hallMapper;

    @InjectMocks
    private PerformanceResponseMapperImpl performanceResponseMapper;

    @BeforeEach
    void init() {
        performanceResponseMapper.setActorResponseMapper(actorResponseMapper);
    }

    @Test
    void correctMappingEntityToDto() {

        Hall hall = new Hall(1, "hall1", "address1", "4544545");
        Actor actor = new Actor(1, "firstname", "lastname", "4555677");
        List<Actor> actors = new ArrayList<>();
        actors.add(actor);
        Performance performance = new Performance(1, "name", "description", hall);
        performance.setActors(actors);

        HallDTO hallDto = new HallDTO(1, "hall1", "address1", "4544545");
        ActorResponseDTO actorDto = new ActorResponseDTO(1, "firstname", "lastname", "4555677", Collections.EMPTY_LIST);
        List<ActorResponseDTO> listOfActorsDto = new ArrayList<>();
        listOfActorsDto.add(actorDto);
        PerformanceResponseDTO expectedPerformanceResponseDto = new PerformanceResponseDTO(1, "name", "description", hallDto, listOfActorsDto);

        Mockito.when(hallMapper.entityToDto(hall)).thenReturn(hallDto);
        Mockito.when(actorResponseMapper.entityToDtoWithoutList(actor)).thenReturn(actorDto);

        PerformanceResponseDTO actualPerformanceResponseDto = performanceResponseMapper.entityToDto(performance);

        Assertions.assertEquals(expectedPerformanceResponseDto, actualPerformanceResponseDto);
        Mockito.verify(hallMapper).entityToDto(hall);
        Mockito.verify(actorResponseMapper).entityToDtoWithoutList(actor);
    }

    @Test
    void mappingWithActorsListNull() {
        Hall hall = new Hall(1, "hall1", "address1", "4544545");
        Performance performance = new Performance(1, "name", "description", hall);

        HallDTO hallDto = new HallDTO(1, "hall1", "address1", "4544545");
        PerformanceResponseDTO expectedPerformanceResponseDto = new PerformanceResponseDTO(1, "name", "description", hallDto, Collections.emptyList());

        Mockito.when(hallMapper.entityToDto(hall)).thenReturn(hallDto);

        PerformanceResponseDTO actualPerformanceResponseDto = performanceResponseMapper.entityToDto(performance);

        Assertions.assertEquals(expectedPerformanceResponseDto, actualPerformanceResponseDto);
        Mockito.verify(hallMapper).entityToDto(hall);
        Mockito.verifyNoInteractions(actorResponseMapper);
    }

}
