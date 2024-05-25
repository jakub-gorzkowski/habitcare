package io.habitcare.web.repository;

import io.habitcare.web.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDateTime;

public interface CheckRepository extends JpaRepository<Check, Long> {
    Check findFirstByHabitIdAndUserIdOrderByCheckDateDesc(Long habitId, Long userId);
    Long countByHabitIdAndUserId(Long habitId, Long userId);
    List<Check> findAllByHabitIdAndUserIdOrderByCheckDateDesc(Long habitId, Long userId);
    Long countByHabitIdAndUserIdAndCheckDateBetween(Long habitId, Long userId, LocalDateTime start, LocalDateTime end);}