package me.nikastroganova.astoncourse.springtask.DTO;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<PerformanceResponseDTO> performances;
}
