package io.habitcare.web.controller;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.mapper.HabitMapper;
import io.habitcare.web.model.Habit;
import io.habitcare.web.model.User;
import io.habitcare.web.service.habit.HabitService;
import io.habitcare.web.service.jwt.JwtService;
import io.habitcare.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

import static io.habitcare.web.mapper.HabitMapper.mapFromHabitDto;
import static io.habitcare.web.mapper.UserMapper.mapToUserDto;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService userService;
    private final HabitService habitService;

    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, HabitService habitService, JwtService jwtService) {
        this.userService = userService;
        this.habitService = habitService;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(mapToUserDto(savedUser), HttpStatus.CREATED);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        UserDto userDto = userService.findUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping(path = "/update-user")
    public ResponseEntity<UserDto> updateUser(@RequestHeader("Authorization") String token,@RequestBody User user) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        if (!userService.exists(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userId);

        User updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(mapToUserDto(updatedUser), HttpStatus.OK);
    }

    @PatchMapping(path = "/update-username")
    public ResponseEntity<Void> updateUsername(@RequestHeader("Authorization") String token, @RequestParam String username) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        userService.updateUsername(userId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/update-image")
    public ResponseEntity<Void> updateImageUrl(@RequestHeader("Authorization") String token, @RequestParam("file") MultipartFile file) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = email + "_" + timestamp + "_" + file.getOriginalFilename();
        Path path = Paths.get("../images/" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userService.updateImageUrl(userId, path.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping(path = "/delete")
    public ResponseEntity<UserDto> deleteUser(@RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/habits")
    public ResponseEntity<List<HabitDto>> getHabits(@RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        List<Habit> habits = userService.findUserHabits(userId);
        List<HabitDto> habitsList = habits.stream()
                .map(HabitMapper::mapToHabitDto)
                .toList();
        return new ResponseEntity<>(habitsList, HttpStatus.OK);
    }

    @GetMapping(path = "/friends")
    public ResponseEntity<List<UserDto>> getFriends(@RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        List<UserDto> friends = userService.getAllFriends(userId);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PatchMapping(path = "/join/{habitId}")
    public ResponseEntity<UserDto> joinHabit(
            @RequestHeader("Authorization") String token,
            @PathVariable("habitId") Long habitId
    ) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);

        User user = userService.findUserDetailsById(userId);
        HabitDto habitDto = habitService.findHabitById(habitId);

        List<Habit> habits = user.getHabits();
        habits.add(mapFromHabitDto(habitDto));
        user.setHabits(habits);

        User updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(mapToUserDto(updatedUser), HttpStatus.OK);
    }

    @GetMapping(path = "/invitations")
    public ResponseEntity<List<UserDto>> getInvites(@RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        List<UserDto> friends = userService.getAllInvitations(userId);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }
}
