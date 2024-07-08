package me.nikastroganova.astoncourse.springtask.controller;

import me.nikastroganova.astoncourse.springtask.dto.HallDTO;
import me.nikastroganova.astoncourse.springtask.exception.IncorrectData;
import me.nikastroganova.astoncourse.springtask.exception.NoSuchHallException;
import me.nikastroganova.astoncourse.springtask.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@ResponseBody
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }


    @GetMapping(value="/halls", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HallDTO> getHalls() {
        return hallService.findAll();
    }

    @GetMapping(value="/halls/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public HallDTO getHall(@PathVariable("id") int id) {
        Optional<HallDTO> hall = hallService.findById(id);
        if (hall.isPresent()) {
            return hall.get();
        }
        else {
            throw new NoSuchHallException("Hall with id " + id + " not found");
        }
    }

    @PostMapping("/halls")
    public HallDTO saveHall(@RequestBody HallDTO hall) {
        return hallService.save(hall);
    }

    @PutMapping("/halls")
    public HallDTO updateHall(@RequestBody HallDTO hall) {
        if (hallService.hallExists(hall.getId())) {
            return hallService.update(hall);
        }
        else {
            throw new NoSuchHallException("Hall with id " + hall.getId() + " not found");
        }
    }

    @DeleteMapping("/halls/{id}")
    public String deleteHall(@PathVariable("id") int id) {
        if (hallService.hallExists(id)) {
            hallService.delete(id);
            return "Hall with id " + id + " was deleted.";

        }
        else {
            throw new NoSuchHallException("Hall with id " + id + " not found");
        }
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchHallException ex) {
        IncorrectData data = new IncorrectData(ex.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
