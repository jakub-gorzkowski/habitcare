package io.habitcare.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusDto {
    private Long id;
    private String name;
}
