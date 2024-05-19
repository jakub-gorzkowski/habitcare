package io.habitcare.web.dto;

import io.habitcare.web.model.FriendshipStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FriendshipDto {
    private Long id;
    private LocalDateTime requestDate;
    private LocalDateTime updateDate;
    private FriendshipStatus status;
    private UserDto sender;
    private UserDto receiver;
}
