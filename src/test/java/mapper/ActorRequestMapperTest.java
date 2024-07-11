package mapper;

import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.mapper.ActorRequestMapper;
import me.nikastroganova.astoncourse.springtask.DTO.ActorRequestDTO;
import me.nikastroganova.astoncourse.springtask.mapper.impl.ActorRequestMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ActorRequestMapperTest {

    private ActorRequestMapper actorRequestMapper;

    @Test
    void correctMappingDtoToEntity() {
        ActorRequestMapper actorRequestMapper = new ActorRequestMapperImpl();
        ActorRequestDTO actorRequestDTO = new ActorRequestDTO(1, "Joan", "Dou", "4545343");

        Actor actor = actorRequestMapper.dtoToEntity(actorRequestDTO);

        Assertions.assertEquals(actorRequestDTO.getId(), actor.getId());
        Assertions.assertEquals(actorRequestDTO.getFirstName(), actor.getFirstname());
        Assertions.assertEquals(actorRequestDTO.getLastName(), actor.getLastname());
        Assertions.assertEquals(actorRequestDTO.getPhoneNumber(), actor.getPhoneNumber());

    }

}
