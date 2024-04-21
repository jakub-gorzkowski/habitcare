package io.habitcare.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SpecificationDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer frequency;
}
