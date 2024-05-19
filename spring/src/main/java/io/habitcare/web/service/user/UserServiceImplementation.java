package io.habitcare.web.service.user;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.mapper.UserMapper;
import io.habitcare.web.model.Friendship;
import io.habitcare.web.model.Habit;
import io.habitcare.web.model.User;
import io.habitcare.web.repository.FriendshipRepository;
import io.habitcare.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.habitcare.web.mapper.UserMapper.mapToUserDto;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean exists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        user.setId(userId);
        return userRepository.findById(userId).map(existingUser -> {
            Optional.ofNullable(user.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(user.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(user.getImageUrl()).ifPresent(existingUser::setImageUrl);
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        return mapToUserDto(user);
    }

    @Override
    public List<UserDto> findUserByUsername(String username) {
        List<User> users = userRepository.findByUsernameContaining(username);
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public List<Habit> findUserHabits(Long userId) {
        List<Habit> habits = userRepository.findUserHabits(userId);
        return habits;
    }

}
