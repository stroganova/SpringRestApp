package me.nikastroganova.astoncourse.springtask.DTO;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private HallDTO hall;
    private List<ActorResponseDTO> actors;
}
