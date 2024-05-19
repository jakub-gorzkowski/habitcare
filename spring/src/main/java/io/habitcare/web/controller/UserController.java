package io.habitcare.web.controller;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.mapper.HabitMapper;
import io.habitcare.web.model.Habit;
import io.habitcare.web.model.User;
import io.habitcare.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.habitcare.web.mapper.UserMapper.mapToUserDto;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(mapToUserDto(savedUser), HttpStatus.CREATED);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto userDto = userService.findUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping(path = "update/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody User user) {
        if (!userService.exists(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userId);

        User updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(mapToUserDto(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "{userId}/habits")
    public ResponseEntity<List<HabitDto>> getHabits(@PathVariable Long userId) {
        List<Habit> habits = userService.findUserHabits(userId);
        List<HabitDto> habitsList = habits.stream()
                .map(HabitMapper::mapToHabitDto)
                .toList();
        return new ResponseEntity<>(habitsList, HttpStatus.OK);
    }

    @GetMapping(path = "{userId}/friends")
    public ResponseEntity<List<UserDto>> getFriends(@PathVariable Long userId) {
        List<UserDto> friends = userService.getAllFriends(userId);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }
}
