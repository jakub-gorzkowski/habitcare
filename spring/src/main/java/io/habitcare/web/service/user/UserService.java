package io.habitcare.web.service.user;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.model.Habit;
import io.habitcare.web.model.User;

import java.util.List;

public interface UserService {
    boolean exists(Long userId);
    User createUser(User user);
    User updateUser(Long userId, User user);
    void deleteById(Long userId);
    List<UserDto> findAllUsers();
    UserDto findUserById(Long userId);
    List<UserDto> findUserByUsername(String username);
    List<Habit> findUserHabits(Long userId);
}
