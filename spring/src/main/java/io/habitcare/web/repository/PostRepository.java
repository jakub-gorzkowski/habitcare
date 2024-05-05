package io.habitcare.web.repository;

import io.habitcare.web.dto.PostDto;
import io.habitcare.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
