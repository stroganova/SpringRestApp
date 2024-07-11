package service;

import me.nikastroganova.astoncourse.springtask.DTO.*;
import me.nikastroganova.astoncourse.springtask.entity.*;
import me.nikastroganova.astoncourse.springtask.mapper.ActorRequestMapper;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceRequestMapper;
import me.nikastroganova.astoncourse.springtask.mapper.PerformanceResponseMapper;
import me.nikastroganova.astoncourse.springtask.repository.ActorRepository;
import me.nikastroganova.astoncourse.springtask.repository.HallRepository;
import me.nikastroganova.astoncourse.springtask.repository.PerformanceRepository;
import me.nikastroganova.astoncourse.springtask.service.impl.PerformanceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PerformanceServiceTest {

    @Mock
    private PerformanceRepository performanceRepository;

    @Mock
    private HallRepository hallRepository;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private PerformanceResponseMapper performanceResponseMapper;

    @Mock
    private PerformanceRequestMapper performanceRequestMapper;

    @Mock
    private ActorRequestMapper actorRequestMapper;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    @Test
    void successfulFindById() {
        HallDTO hallDto = new HallDTO(1, "Hall 1", "address", "5555555");
        PerformanceResponseDTO performance = new PerformanceResponseDTO(1, "name", "description", hallDto, Collections.emptyList());

        Hall hall = new Hall(1, "Hall 1", "address", "5555555");
        Performance performanceEntity = new Performance(1, "name", "description", hall);

        Mockito.when(performanceRepository.findById(1)).thenReturn(Optional.of(performanceEntity));
        Mockito.when(performanceResponseMapper.entityToDto(performanceEntity)).thenReturn(performance);
        Optional<PerformanceResponseDTO> actualActorResponseDto = performanceService.findById(1);

        Mockito.verify(performanceRepository).findById(1);
        Mockito.verify(performanceResponseMapper).entityToDto(performanceEntity);
        assertTrue(actualActorResponseDto.isPresent());
    }

    @Test
    void couldntFindByIdNotExist() {
        Mockito.when(performanceRepository.findById(1)).thenReturn(Optional.empty());

        Optional<PerformanceResponseDTO> actualActorResponseDto = performanceService.findById(1);
        assertTrue(!actualActorResponseDto.isPresent());
        Mockito.verify(performanceRepository).findById(1);
        Mockito.verifyNoMoreInteractions(performanceResponseMapper);
    }

    @Test
    void successfullyGetAllPerformances() {
        List<Performance> performances = new ArrayList<>();
        Performance performance1 = new Performance(1, "name1", "description1", new Hall(1, "Hall 1", "address", "5555555"));
        Performance performance2 = new Performance(2, "name2", "description2", new Hall(2, "Hall 2", "address", "6666666"));
        performances.add(performance1);
        performances.add(performance2);

        List<PerformanceResponseDTO> expectedPerformances = new ArrayList<>();
        PerformanceResponseDTO performanceResponse1 = new PerformanceResponseDTO(1, "name1", "description1", new HallDTO(1, "Hall 1", "address", "5555555"), Collections.emptyList());
        PerformanceResponseDTO performanceResponse2 = new PerformanceResponseDTO(2, "name2", "description2", new HallDTO(2, "Hall 2", "address", "6666666"), Collections.emptyList());
        expectedPerformances.add(performanceResponse1);
        expectedPerformances.add(performanceResponse2);

        Mockito.when(performanceRepository.findAll()).thenReturn(performances);
        Mockito.when(performanceResponseMapper.entityToDto(performance1)).thenReturn(performanceResponse1);
        Mockito.when(performanceResponseMapper.entityToDto(performance2)).thenReturn(performanceResponse2);

        List<PerformanceResponseDTO> actualPerformances = performanceService.findAll();

        Mockito.verify(performanceRepository).findAll();
        Mockito.verify(performanceResponseMapper).entityToDto(performance1);
        Mockito.verify(performanceResponseMapper).entityToDto(performance2);
        Assertions.assertEquals(expectedPerformances, actualPerformances);

    }

    @Test
    void successfullySavePerformance() {
        Hall hall = new Hall(1, "Hall 1", "address", "5555555");
        Performance performance = new Performance(1, "name", "description", hall);
        PerformanceRequestDTO performanceRequestDto = new PerformanceRequestDTO(1, "name", "description", hall.getId());
        PerformanceResponseDTO expectedPerformanceResponseDto = new PerformanceResponseDTO(1, "name", "description", new HallDTO(1, "Hall 1", "address", "5555555"), Collections.emptyList());

        Mockito.when(hallRepository.findById(hall.getId())).thenReturn(Optional.of(hall));
        Mockito.when(performanceRequestMapper.dtoToEntity(performanceRequestDto, hall)).thenReturn(performance);
        Mockito.when(performanceRepository.save(performance)).thenReturn(performance);
        Mockito.when(performanceResponseMapper.entityToDto(performance)).thenReturn(expectedPerformanceResponseDto);

        PerformanceResponseDTO actualPerformanceResponseDto = performanceService.save(performanceRequestDto);

        assertEquals(expectedPerformanceResponseDto, actualPerformanceResponseDto);
        Mockito.verify(hallRepository).findById(hall.getId());
        Mockito.verify(performanceRequestMapper).dtoToEntity(performanceRequestDto, hall);
        Mockito.verify(performanceRepository).save(performance);
        Mockito.verify(performanceResponseMapper).entityToDto(performance);

    }

    @Test
    void successfullyUpdatePerformance() {
        Hall hall = new Hall(1, "Hall 1", "address", "5555555");
        Performance performance = new Performance(1, "name", "description", hall);
        PerformanceRequestDTO performanceRequestDto = new PerformanceRequestDTO(1, "newName", "newDescription", hall.getId());
        PerformanceResponseDTO expectedPerformanceResponseDto = new PerformanceResponseDTO(1, "newName", "newDescription",
                                            new HallDTO(1, "Hall 1", "address", "5555555"),
                                            Collections.emptyList());

        Mockito.when(hallRepository.findById(hall.getId())).thenReturn(Optional.of(hall));
        Mockito.when(performanceRequestMapper.dtoToEntity(performanceRequestDto, hall)).thenReturn(performance);
        Mockito.when(performanceRepository.update(performance)).thenReturn(performance);
        Mockito.when(performanceResponseMapper.entityToDto(performance)).thenReturn(expectedPerformanceResponseDto);

        PerformanceResponseDTO actualPerformanceResponseDto = performanceService.update(performanceRequestDto);

        Mockito.verify(hallRepository).findById(hall.getId());
        Mockito.verify(performanceRequestMapper).dtoToEntity(performanceRequestDto, hall);
        Mockito.verify(performanceRepository).update(performance);
        Mockito.verify(performanceResponseMapper).entityToDto(performance);
        assertEquals(expectedPerformanceResponseDto, actualPerformanceResponseDto);
    }

}
