package io.habitcare.web.service.habit;

import io.habitcare.web.dto.CheckDto;
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
    CheckDto AddCheckHabit(Long habitId, Long userId);
    boolean isTodayChecked(Long habitId, Long userId);

    void removeCheckHabit(Long habitId, Long userId);

    void assignHabitToUser(Long userId, Long habitId);
    Long countChecks(Long habitId, Long userId);
    Long getStreak(Long habitId, Long userId);
}
