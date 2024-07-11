package me.nikastroganova.astoncourse.springtask.DTO;

import lombok.*;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceRequestDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer hallId;
}
