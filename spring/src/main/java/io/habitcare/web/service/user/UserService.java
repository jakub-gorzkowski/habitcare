package io.habitcare.web.service.user;

import io.habitcare.web.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(Long userId);
}
