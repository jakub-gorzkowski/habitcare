package io.habitcare.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
    private Long id;
    private String description;
    private LocalDateTime postDate;
    private Long moodId;
}
