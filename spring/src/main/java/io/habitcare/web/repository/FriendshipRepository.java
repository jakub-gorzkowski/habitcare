package io.habitcare.web.repository;

import io.habitcare.web.model.Friendship;
import io.habitcare.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FriendshipRepository extends JpaRepository<Friendship, Long> {


    @Query("SELECT f FROM Friendship f WHERE (f.sender = ?1 OR f.receiver = ?1) AND f.status = 'ACCEPTED'")
    List<Friendship> findAllAcceptedFriendships(User user);

}