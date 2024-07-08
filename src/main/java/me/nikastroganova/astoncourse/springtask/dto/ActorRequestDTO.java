package me.nikastroganova.astoncourse.springtask.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorRequestDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
