package io.habitcare.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HabitDto {
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private String description;
}
