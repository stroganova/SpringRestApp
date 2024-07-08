package me.nikastroganova.astoncourse.springtask.dto;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class HallDTO {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
}
