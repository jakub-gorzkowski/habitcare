package io.habitcare.web.mapper;

import io.habitcare.web.dto.UserDto;
import io.habitcare.web.model.User;

public class UserMapper {
    public static User mapFromUserDto(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .imageUrl(userDto.getImageUrl())
                .username(userDto.getUsername())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .username(user.getRealUsername())
                .build();
    }
}
