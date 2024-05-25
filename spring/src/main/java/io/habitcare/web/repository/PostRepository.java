package io.habitcare.web.repository;

import io.habitcare.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
    boolean existsByUserIdAndPostDateBetween(Long userId, LocalDateTime start, LocalDateTime end);
    Optional<Post> findByUserIdAndPostDateBetween(Long userId, LocalDateTime start, LocalDateTime end);

}
