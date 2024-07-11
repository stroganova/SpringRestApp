package me.nikastroganova.astoncourse.springtask.controller;

import me.nikastroganova.astoncourse.springtask.DTO.PerformanceRequestDTO;
import me.nikastroganova.astoncourse.springtask.DTO.PerformanceResponseDTO;
import me.nikastroganova.astoncourse.springtask.exception.NoSuchPerformanceException;
import me.nikastroganova.astoncourse.springtask.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import me.nikastroganova.astoncourse.springtask.exception.IncorrectData;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@ResponseBody
public class PerformanceController {

    private final PerformanceService performanceService;

    @Autowired
    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/performances")
    public List<PerformanceResponseDTO> getPerformances() {
        return performanceService.findAll();
    }

    @GetMapping("performances/{id}")
    public PerformanceResponseDTO getPerformance(@PathVariable("id") int id) {
        Optional<PerformanceResponseDTO> performance = performanceService.findById(id);
        if (performance.isPresent()) {
            return performance.get();
        }
        else {
            throw new NoSuchPerformanceException("Performance with id " + id + " not found");
        }
    }

    @PostMapping("/performances")
    public PerformanceResponseDTO savePerformance(@RequestBody PerformanceRequestDTO performance) {
        return performanceService.save(performance);
    }

    @PutMapping("/performances")
    public PerformanceResponseDTO updatePerformance(@RequestBody PerformanceRequestDTO performance) {
        if(performanceService.performanceExists(performance.getId())) {
            return performanceService.update(performance);
        }
        else {
            throw new NoSuchPerformanceException("Performance with id " + performance.getId() + " not found");
        }

    }

    @DeleteMapping("/performances/{id}")
    public String deletePerformance(@PathVariable("id") int id) {
        if(performanceService.performanceExists(id)) {
            performanceService.delete(id);
            return "performance with id " + id + " was deleted.";

        }
        else {
            throw new NoSuchPerformanceException("Performance with id " + id + " not found");
        }
    }


    @PostMapping("/performances/{performanceId}/addActor/{actorId}")
    public String addPerformance(@PathVariable("performanceId") int performanceId, @PathVariable("actorId") int actorId) {
        if (performanceService.addActor(performanceId, actorId)) {
            return "Actor with id " + actorId + " added to performance with id " + performanceId;
        }
        return "Actor with id " + actorId + " could not be added to performance with id " + performanceId;
    }

    @DeleteMapping("/performances/{performanceId}/removeActor/{actorId}")
    public String removePerformance(@PathVariable("performanceId") int performanceId, @PathVariable("actorId") int actorId) {
        if (performanceService.removeActor(performanceId, actorId)) {
            return "Actor with id " + actorId + " removed from performance with id " + performanceId;
        }
        return "Actor with id " + actorId + " could not be removed from performance with id " + performanceId;
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchPerformanceException ex) {
        IncorrectData data = new IncorrectData(ex.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
