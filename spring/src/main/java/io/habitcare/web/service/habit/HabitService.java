package io.habitcare.web.service.habit;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.model.Habit;

import java.util.List;

public interface HabitService {
    boolean exists(Long habitId);
    List<HabitDto> getAllHabits();
    HabitDto findHabitById(Long id);
    Habit createHabit(Habit habit);
    Habit updateHabit(Long habitId, Habit habit);
    void deleteHabit(Long habitId);
}
