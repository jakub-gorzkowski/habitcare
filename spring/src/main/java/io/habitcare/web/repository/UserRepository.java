package io.habitcare.web.repository;

import io.habitcare.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContaining(String username);
}
