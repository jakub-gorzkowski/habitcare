package io.habitcare.web.mapper;

import io.habitcare.web.dto.UserDto;
import io.habitcare.web.model.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .username(user.getUsername())
                .build();
    }
}
