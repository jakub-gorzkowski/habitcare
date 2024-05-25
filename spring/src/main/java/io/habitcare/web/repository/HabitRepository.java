package io.habitcare.web.repository;

import io.habitcare.web.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {

}
