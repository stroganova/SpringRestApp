package service;

import me.nikastroganova.astoncourse.springtask.DTO.HallDTO;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.mapper.HallMapper;
import me.nikastroganova.astoncourse.springtask.repository.HallRepository;
import me.nikastroganova.astoncourse.springtask.service.impl.HallServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HallServiceTest {

    @Mock
    private HallRepository hallRepository;

    @Mock
    private HallMapper hallMapper;

    @InjectMocks
    private HallServiceImpl hallService;

    @Test
    void successfullyReturnHallById() {
        Hall hall = new Hall(1, "Hall", "address", "5555555");
        HallDTO hallDTO = new HallDTO(1, "Hall", "address", "5555555");

        Mockito.when(hallRepository.findById(1)).thenReturn(Optional.of(hall));
        Mockito.when(hallMapper.entityToDto(hall)).thenReturn(hallDTO);

        Optional<HallDTO> actualHallDto = hallService.findById(1);
        assertTrue(actualHallDto.isPresent());
        Mockito.verify(hallRepository).findById(1);
        Mockito.verify(hallMapper).entityToDto(hall);
    }

    @Test
    void unsuccessfullyReturnHallById() {
        Mockito.when(hallRepository.findById(1)).thenReturn(Optional.empty());

        Optional<HallDTO> actualHallDto = hallService.findById(1);
        assertTrue(!actualHallDto.isPresent());
        Mockito.verify(hallRepository).findById(1);
        Mockito.verifyNoMoreInteractions(hallMapper);
    }

    @Test
    void findAllHall() {
        Hall firtsHall = new Hall(1, "Hall", "address", "5555555");
        Hall secondHall = new Hall(2,"another Hall", "another address", "7777777");
        HallDTO firstHallDTO = new HallDTO(1, "Hall", "address", "5555555");
        HallDTO secondHallDTO = new HallDTO(2, "another Hall", "another address", "7777777");

        List<Hall>  hallList = new ArrayList<Hall>();
        hallList.add(firtsHall);
        hallList.add(secondHall);

        List<HallDTO> hallDTOList = new ArrayList<HallDTO>();
        hallDTOList.add(firstHallDTO);
        hallDTOList.add(secondHallDTO);

        Mockito.when(hallRepository.findAll()).thenReturn(hallList);
        Mockito.when(hallMapper.entityToDto(firtsHall)).thenReturn(firstHallDTO);
        Mockito.when(hallMapper.entityToDto(secondHall)).thenReturn(secondHallDTO);
        List actualList = hallService.findAll();

        assertTrue(hallDTOList.size() == actualList.size());
        assertIterableEquals(hallDTOList, actualList);
        Mockito.verify(hallRepository).findAll();
        Mockito.verify(hallMapper).entityToDto(firtsHall);
    }

    @Test
    void successfullyUpdateHall() {
        Hall hall = new Hall(1, "Updated Hall", "updated address", "6666666");
        HallDTO hallDTO = new HallDTO(1, "Updated Hall", "updated address", "6666666");

        Mockito.when(hallMapper.dtoToEntity(hallDTO)).thenReturn(hall);
        Mockito.when(hallRepository.update(hall)).thenReturn(hall);
        Mockito.when(hallMapper.entityToDto(hall)).thenReturn(hallDTO);

        HallDTO actualHallDto = hallService.update(hallDTO);

        assertTrue(actualHallDto.equals(hallDTO));
        Mockito.verify(hallMapper).dtoToEntity(hallDTO);
        Mockito.verify(hallRepository).update(hall);
        Mockito.verify(hallMapper).entityToDto(hall);
    }

    @Test
    void successfullySaveHall() {
        Hall hall = new Hall(1, "New Hall", "new address", "8888888");
        HallDTO hallDTO = new HallDTO(1, "New Hall", "new address", "8888888");

        Mockito.when(hallMapper.dtoToEntity(hallDTO)).thenReturn(hall);
        Mockito.when(hallRepository.save(hall)).thenReturn(hall);
        Mockito.when(hallMapper.entityToDto(hall)).thenReturn(hallDTO);

        HallDTO actualHallDto = hallService.save(hallDTO);

        assertTrue(actualHallDto.equals(hallDTO));
        Mockito.verify(hallMapper).dtoToEntity(hallDTO);
        Mockito.verify(hallRepository).save(hall);
        Mockito.verify(hallMapper).entityToDto(hall);
    }

}
