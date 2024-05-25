package io.habitcare.web.service.habit;

import io.habitcare.web.mapper.CheckMapper;
import io.habitcare.web.model.Check;
import io.habitcare.web.dto.CheckDto;
import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.mapper.HabitMapper;
import io.habitcare.web.model.Habit;
import io.habitcare.web.model.User;
import io.habitcare.web.repository.CheckRepository;
import io.habitcare.web.repository.UserRepository;
import io.habitcare.web.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static io.habitcare.web.mapper.HabitMapper.mapToHabitDto;

@Service
public class HabitServiceImplementation implements HabitService {
    private final HabitRepository habitRepository;
    private final CheckRepository checkRepository;
    private final UserRepository userRepository;


    @Autowired
    public HabitServiceImplementation(HabitRepository habitRepository, CheckRepository checkRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.checkRepository = checkRepository;
        this.userRepository = userRepository;

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
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + id));

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
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + habitId));

        for (User user : habit.getUsers()) {
            user.getHabits().remove(habit);
            userRepository.save(user);
        }
        habitRepository.deleteById(habitId);
    }

    @Override
    public CheckDto AddCheckHabit(Long habitId, Long userId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + habitId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Check newCheck = Check.builder()
                .habit(habit)
                .user(user)
                .build();
        checkRepository.save(newCheck);
        return CheckMapper.mapToCheckDto(newCheck);
    }

    @Override
    public boolean isTodayChecked(Long habitId, Long userId) {
        Check check = checkRepository.findFirstByHabitIdAndUserIdOrderByCheckDateDesc(habitId, userId);
        if (check != null) {
            LocalDateTime checkDate = check.getCheckDate();
            return checkDate.toLocalDate().equals(LocalDate.now());
        }
        return false;
    }

    @Override
    public void removeCheckHabit(Long habitId, Long userId) {
        Check check = checkRepository.findFirstByHabitIdAndUserIdOrderByCheckDateDesc(habitId, userId);
        if (check != null && check.getCheckDate().toLocalDate().equals(LocalDate.now())) {
            checkRepository.delete(check);
        }
    }

    @Override
    public void assignHabitToUser(Long userId, Long habitId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found with id: " + habitId));

        List<Habit> userHabits = user.getHabits();
        userHabits.add(habit);
        user.setHabits(userHabits);

        userRepository.save(user);
    }

    @Override
    public Long countChecks(Long habitId, Long userId) {
        return checkRepository.countByHabitIdAndUserId(habitId, userId);
    }


    @Override
    public Long getStreak(Long habitId, Long userId) {
        List<Check> checks = checkRepository.findAllByHabitIdAndUserIdOrderByCheckDateDesc(habitId, userId);
        LocalDate currentDate = LocalDate.now();
        Long streak = 0L;

        for (Check check : checks) {
            if (check.getCheckDate().toLocalDate().equals(currentDate)) {
                streak++;
                currentDate = currentDate.minusDays(1);
            } else {
                break;
            }
        }

        return streak;
    }

    @Override
    public Long countMonthlyChecks(Long habitId, Long userId) {
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = LocalDateTime.now();
        return checkRepository.countByHabitIdAndUserIdAndCheckDateBetween(habitId, userId, startOfMonth, endOfMonth);
    }

    @Override
    public Long countMonthlyChecksPercent(Long habitId, Long userId) {
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime today = LocalDateTime.now();
        Long count = checkRepository.countByHabitIdAndUserIdAndCheckDateBetween(habitId, userId, startOfMonth, today);
        long totalDays = ChronoUnit.DAYS.between(startOfMonth, today) + 1;
        return count * 100 / totalDays;
    }

    @Override
    public Long countDailyChecks(Long userId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        return checkRepository.countByUserIdAndCheckDateBetween(userId, startOfDay, endOfDay);
    }
}
