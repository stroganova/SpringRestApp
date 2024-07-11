package me.nikastroganova.astoncourse.springtask.DTO;

import lombok.*;


@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class HallDTO {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
}
