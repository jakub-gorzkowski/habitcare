package io.habitcare.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FriendshipDto {
    private Long id;
    private LocalDateTime requestDate;
}
