package io.habitcare.web.service.habit;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.mapper.HabitMapper;
import io.habitcare.web.model.Habit;
import io.habitcare.web.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static io.habitcare.web.mapper.HabitMapper.mapToHabitDto;

@Service
public class HabitServiceImplementation implements HabitService {
    private final HabitRepository habitRepository;

    @Autowired
    public HabitServiceImplementation(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public boolean exists(Long habitId) {
        return habitRepository.existsById(habitId);
    }

    @Override
    public List<HabitDto> getAllHabits() {
        List<Habit> habits = habitRepository.findAll();
        return habits.stream()
                .map(HabitMapper::mapToHabitDto)
                .toList();
    }

    @Override
    public HabitDto findHabitById(Long id) {
        Habit habit = habitRepository.findById(id).get();
        return mapToHabitDto(habit);
    }

    @Override
    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    @Override
    public Habit updateHabit(Long habitId, Habit habit) {
        habit.setId(habitId);
        return habitRepository.findById(habitId).map(existingHabit -> {
            Optional.ofNullable(habit.getName()).ifPresent(existingHabit::setName);
            Optional.ofNullable(habit.getDescription()).ifPresent(existingHabit::setDescription);
            return habitRepository.save(existingHabit);
        }).orElseThrow(() -> new RuntimeException("Habit does not exist"));
    }

    @Override
    public void deleteHabit(Long habitId) {
        habitRepository.deleteById(habitId);
    }
}
