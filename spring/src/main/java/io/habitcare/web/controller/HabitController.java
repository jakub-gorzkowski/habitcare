package io.habitcare.web.controller;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.model.Habit;
import io.habitcare.web.service.habit.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.habitcare.web.mapper.HabitMapper.mapFromHabitDto;
import static io.habitcare.web.mapper.HabitMapper.mapToHabitDto;

@RestController
@RequestMapping(path = "/api/habits")
public class HabitController {
    private final HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<HabitDto> addHabit(@RequestBody HabitDto habitDto) {
        Habit habit = mapFromHabitDto(habitDto);
        Habit savedHabit = habitService.createHabit(habit);
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
        habitService.deleteHabit(habitId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
