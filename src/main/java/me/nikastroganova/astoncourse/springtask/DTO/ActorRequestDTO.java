package me.nikastroganova.astoncourse.springtask.DTO;

import lombok.*;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ActorRequestDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
