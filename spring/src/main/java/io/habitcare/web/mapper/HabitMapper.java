package io.habitcare.web.mapper;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.model.Habit;

public class HabitMapper {

    public static Habit mapFromHabitDto(HabitDto habitDto) {
        return Habit.builder()
                .id(habitDto.getId())
                .name(habitDto.getName())
                .description(habitDto.getDescription())
                .build();
    }

    public static HabitDto mapToHabitDto(Habit habit) {
        return HabitDto.builder()
                .id(habit.getId())
                .name(habit.getName())
                .description(habit.getDescription())
                .build();
    }
}
