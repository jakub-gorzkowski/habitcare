package io.habitcare.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoodDto {
    private Long id;
    private String name;
    private Byte value;
}
