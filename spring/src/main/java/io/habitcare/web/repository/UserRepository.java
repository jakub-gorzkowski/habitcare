package io.habitcare.web.repository;

import io.habitcare.web.dto.HabitDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.model.Habit;
import io.habitcare.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContaining(String username);
    @Query("SELECT h FROM Habit h JOIN h.users u WHERE u.id = :userId")
    List<Habit> findUserHabits(@Param("userId") Long userId);

    Optional<User> findByEmail(String email);
}
