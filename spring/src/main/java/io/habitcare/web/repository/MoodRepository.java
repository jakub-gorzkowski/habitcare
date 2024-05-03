package io.habitcare.web.repository;

import io.habitcare.web.model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoodRepository extends JpaRepository<Mood, Long> {
    Optional<Mood> findById(Long moodId);
}
