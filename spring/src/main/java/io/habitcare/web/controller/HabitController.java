package io.habitcare.web.controller;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.dto.CheckDto;
import io.habitcare.web.model.Habit;
import io.habitcare.web.service.habit.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.habitcare.web.service.jwt.JwtService;
import io.habitcare.web.service.user.UserService;

import java.time.LocalDate;
import java.util.List;

import static io.habitcare.web.mapper.HabitMapper.mapFromHabitDto;
import static io.habitcare.web.mapper.HabitMapper.mapToHabitDto;

@RestController
@RequestMapping(path = "/api/habits")
public class HabitController {
    private final HabitService habitService;
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public HabitController(HabitService habitService, UserService userService, JwtService jwtService) {
        this.habitService = habitService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<HabitDto> addHabit(@RequestBody HabitDto habitDto, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);

        Habit habit = mapFromHabitDto(habitDto);
        Habit savedHabit = habitService.createHabit(habit);
        Long habitId = savedHabit.getId();
        habitService.assignHabitToUser(userId,habitId);
        return new ResponseEntity<>(mapToHabitDto(savedHabit), HttpStatus.CREATED);

    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<HabitDto>> returnAllHabits() {
        List<HabitDto> habits = habitService.getAllHabits();
        return new ResponseEntity<>(habits, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{habitId}")
    public ResponseEntity<HabitDto> getHabit(@PathVariable Long habitId) {
        HabitDto habitDto = habitService.findHabitById(habitId);
        return new ResponseEntity<>(habitDto, HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{habitId}")
    public ResponseEntity<HabitDto> updateHabit(@PathVariable Long habitId, @RequestBody HabitDto habitDto) {
        if (!habitService.exists(habitId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        habitDto.setId(habitId);
        Habit habit = mapFromHabitDto(habitDto);
        Habit updatedHabit = habitService.updateHabit(habitId, habit);
        return new ResponseEntity<>(mapToHabitDto(updatedHabit), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{habitId}")
    public ResponseEntity<HabitDto> deleteHabit(@PathVariable Long habitId) {
        if (!habitService.exists(habitId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        habitService.deleteHabit(habitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/add-check/{habitId}")
    public ResponseEntity<CheckDto> AddCheckHabit(@PathVariable Long habitId,@RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        if(habitService.isTodayChecked(habitId, userId)) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        CheckDto checkDto = habitService.AddCheckHabit(habitId, userId);
        return new ResponseEntity<>(checkDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/remove-check/{habitId}")
    public ResponseEntity<Void> removeCheckHabit(@PathVariable Long habitId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        if(!habitService.isTodayChecked(habitId, userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        habitService.removeCheckHabit(habitId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/is-checked/{habitId}")
    public ResponseEntity<Boolean> isTodayChecked(@PathVariable Long habitId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        boolean isChecked = habitService.isTodayChecked(habitId, userId);
        return new ResponseEntity<>(isChecked, HttpStatus.OK);
    }

    @GetMapping(path = "/count-checks/{habitId}")
    public ResponseEntity<Long> countChecks(@PathVariable Long habitId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        Long count = habitService.countChecks(habitId, userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(path = "/streak/{habitId}")
    public ResponseEntity<Long> getStreak(@PathVariable Long habitId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        Long streak = habitService.getStreak(habitId, userId);
        return new ResponseEntity<>(streak, HttpStatus.OK);
    }

    @GetMapping(path = "/monthly-checks/{habitId}")
    public ResponseEntity<Long> countMonthlyChecks(@PathVariable Long habitId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);

        Long count = habitService.countMonthlyChecks(habitId, userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(path = "/monthly-percent/{habitId}")
    public ResponseEntity<Long> countMonthlyChecksPercent(@PathVariable Long habitId, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);

        Long count = habitService.countMonthlyChecksPercent(habitId, userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping(path = "/daily-checks/{date}")
    public ResponseEntity<Long> countDailyChecks(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestHeader("Authorization") String token) {
        String email = jwtService.getEmailFromToken(token);
        Long userId = userService.getUserIdByEmail(email);
        Long count = habitService.countDailyChecks(userId, date);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
