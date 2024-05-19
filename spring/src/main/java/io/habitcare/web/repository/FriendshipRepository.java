package io.habitcare.web.repository;

import io.habitcare.web.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("SELECT f FROM Friendship f WHERE (f.sender.id = ?1 OR f.receiver.id = ?1) AND f.status = 'ACCEPTED'")
    List<Friendship> findAllAcceptedFriendships(Long userId);

    @Query("SELECT f FROM Friendship f WHERE (f.sender.id = ?2 and f.receiver.id = ?1) AND f.status = 'REQUESTED'")
    Friendship findFriendshipByUsers(Long receiverId, Long senderId);
}