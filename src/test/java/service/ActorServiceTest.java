package service;

import me.nikastroganova.astoncourse.springtask.DTO.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.DTO.ActorResponseDTO;
import me.nikastroganova.astoncourse.springtask.mapper.ActorRequestMapper;
import me.nikastroganova.astoncourse.springtask.mapper.ActorResponseMapper;
import me.nikastroganova.astoncourse.springtask.repository.ActorRepository;
import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.service.impl.ActorServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private ActorRequestMapper actorRequestMapper;

    @Mock
    private ActorResponseMapper actorResponseMapper;

    @InjectMocks
    private ActorServiceImpl actorService;

    @Test
    void successfulActorSave() {
        Actor actor = new Actor(1, "John", "Doe", "1234567890");
        ActorRequestDTO actorRequestDto = new ActorRequestDTO(1, "John", "Doe", "12345678");
        ActorResponseDTO actorResponseDto = new ActorResponseDTO(1, "John", "Doe", "1234567890", Collections.emptyList());

        Mockito.when(actorRequestMapper.dtoToEntity(actorRequestDto)).thenReturn(actor);
        Mockito.when(actorRepository.save(actor)).thenReturn(actor);
        Mockito.when(actorResponseMapper.entityToDto(actor)).thenReturn(actorResponseDto);

        ActorResponseDTO actualActorResponseDto = actorService.save(actorRequestDto);

        Mockito.verify(actorRequestMapper).dtoToEntity(actorRequestDto);
        Mockito.verify(actorRepository).save(actor);
        Mockito.verify(actorResponseMapper).entityToDto(actor);
    }

   @Test
    void successfulFindById() {
        Actor actor = new Actor(1, "John", "Doe", "1234567890");
        ActorResponseDTO actorResponseDto = new ActorResponseDTO(1, "John", "Doe", "1234567890", Collections.emptyList());

        Mockito.when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        Mockito.when(actorResponseMapper.entityToDto(actor)).thenReturn(actorResponseDto);

        Optional<ActorResponseDTO> actualActorResponseDto = actorService.findById(1);

        Mockito.verify(actorRepository).findById(1);
        Mockito.verify(actorResponseMapper).entityToDto(actor);
        Assertions.assertTrue(actualActorResponseDto.isPresent());
   }

   @Test
    void couldntFindActorNotExist() {

        Mockito.when(actorRepository.findById(1)).thenReturn(Optional.empty());

        Optional<ActorResponseDTO> actualActorResponseDto = actorService.findById(1);
        Assertions.assertFalse(actualActorResponseDto.isPresent());

   }

   @Test
    void successfullyGetAllActors() {
        List<Actor> actors = new ArrayList<Actor>();
        Actor firstActor = new Actor(1, "John", "Dou", "5454354353");
        Actor secondActor = new Actor(2, "Jane", "Doe", "3453453453");
        actors.add(firstActor);
        actors.add(secondActor);

        List<ActorResponseDTO> expectedActors = new ArrayList<ActorResponseDTO>();
        ActorResponseDTO firstActorResponse = new ActorResponseDTO(1, "John", "Doe", "5454354353", Collections.emptyList());
        ActorResponseDTO secondActorResponse = new ActorResponseDTO(2, "Jane", "Doe", "3453453453", Collections.emptyList());
        expectedActors.add(firstActorResponse);
        expectedActors.add(secondActorResponse);

        Mockito.when(actorRepository.findAll()).thenReturn(actors);
        Mockito.when(actorResponseMapper.entityToDto(firstActor)).thenReturn(firstActorResponse);
        Mockito.when(actorResponseMapper.entityToDto(secondActor)).thenReturn(secondActorResponse);

        List<ActorResponseDTO> actualActors = actorService.findAll();

        Mockito.verify(actorRepository).findAll();
        Mockito.verify(actorResponseMapper).entityToDto(firstActor);
        Mockito.verify(actorResponseMapper).entityToDto(secondActor);
        Assertions.assertEquals(expectedActors, actualActors);

   }

   @Test
   void successfullyUpdateActor() {
       Actor actor = new Actor(1, "John", "Doe", "1234");
       ActorRequestDTO actorRequestDto = new ActorRequestDTO(1, "John", "Doe", "12345678");
       ActorResponseDTO actorResponseDto = new ActorResponseDTO(1, "John", "Doe", "1234567890", Collections.emptyList());

       Mockito.when(actorRequestMapper.dtoToEntity(actorRequestDto)).thenReturn(actor);
       Mockito.when(actorRepository.update(actor)).thenReturn(actor);
       Mockito.when(actorResponseMapper.entityToDto(actor)).thenReturn(actorResponseDto);

       ActorResponseDTO actualActorResponseDto = actorService.update(actorRequestDto);

       assertTrue(actualActorResponseDto.equals(actorResponseDto));
       Mockito.verify(actorRequestMapper).dtoToEntity(actorRequestDto);
       Mockito.verify(actorRepository).update(actor);
       Mockito.verify(actorResponseMapper).entityToDto(actor);

   }

}
