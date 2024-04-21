package io.habitcare.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HabitDto {
    private Long id;
    private String name;
    private String description;
}
