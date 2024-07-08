package me.nikastroganova.astoncourse.springtask.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceRequestDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer hallId;
}
