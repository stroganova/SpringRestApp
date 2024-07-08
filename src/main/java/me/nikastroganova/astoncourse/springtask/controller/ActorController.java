package me.nikastroganova.astoncourse.springtask.controller;

import me.nikastroganova.astoncourse.springtask.dto.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.dto.ActorResponseDTO;
import me.nikastroganova.astoncourse.springtask.exception.IncorrectData;
import me.nikastroganova.astoncourse.springtask.exception.NoSuchActorException;
import me.nikastroganova.astoncourse.springtask.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public List<ActorResponseDTO> getAllActors() {
        return actorService.findAll();
    }

    @GetMapping("/actors/{id}")
    public ActorResponseDTO getActorById(@PathVariable("id") int id) {
        Optional<ActorResponseDTO> actor = actorService.findById(id);
        if (actor.isPresent()) {
            return actor.get();
        }
        else {
            throw new NoSuchActorException("Actor with id " + id + " not found");
        }
    }

    @PostMapping("/actors")
    public ActorResponseDTO saveActor(@RequestBody ActorRequestDTO actor) {
        return actorService.save(actor);
    }

    @PutMapping("/actors")
    public ActorResponseDTO updateActor(@RequestBody ActorRequestDTO actor) {
        if (actorService.actorExists(actor.getId())) {
            return actorService.update(actor);
        }
        else {
            throw new NoSuchActorException("Actor with id " + actor.getId() + " does not exist");
        }
    }

    @DeleteMapping("/actors/{id}")
    public String deleteActor(@PathVariable("id") int id) {
        if (actorService.actorExists(id)) {
            actorService.delete(id);
            return "Actor with id " + id + " deleted";
        }
        else {
            throw new NoSuchActorException("Actor with id " + id + " does not exist");
        }
    }

    @PostMapping("/actors/{actorId}/addPerformance/{performanceId}")
    public String addPerformance(@PathVariable("actorId") int actorId, @PathVariable("performanceId") int performanceId) {
        if (actorService.addPerformance(actorId, performanceId)) {
            return "Performance with id " + performanceId + " added to actor with id " + actorId;
        }
        return "Performance with id " + performanceId + " could not be added to actor with id " + actorId;
    }

    @DeleteMapping("/actors/{actorId}/removePerformance/{performanceId}")
    public String removePerformance(@PathVariable("actorId") int actorId, @PathVariable("performanceId") int performanceId) {
        if (actorService.removePerformance(actorId, performanceId)) {
            return "Performance with id " + performanceId + " removed from actor with id " + actorId;
        }
        return "Performance with id " + performanceId + " could not be removed from actor with id " + actorId;
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchActorException ex) {
        IncorrectData data = new IncorrectData(ex.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
