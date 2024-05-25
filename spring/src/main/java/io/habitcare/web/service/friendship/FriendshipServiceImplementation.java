package io.habitcare.web.service.friendship;

import io.habitcare.web.dto.FriendshipDto;
import io.habitcare.web.mapper.FriendshipMapper;
import io.habitcare.web.model.Friendship;
import io.habitcare.web.model.FriendshipStatus;
import io.habitcare.web.model.User;
import io.habitcare.web.repository.FriendshipRepository;
import io.habitcare.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImplementation implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipServiceImplementation(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean exists(Long FriendshipId) {
        return friendshipRepository.existsById(FriendshipId);
    }

    @Override
    public boolean existsFriendshipByUsers(Long senderId, Long receiverId) {
        Friendship friendship = friendshipRepository.findFriendshipByUsers(senderId, receiverId);
        return friendship != null;
    }

    @Override
    public String getFriendshipStatus(Long senderId, Long receiverId) {
        Friendship friendship = friendshipRepository.findFriendshipByUsers(senderId, receiverId);
        return friendship.getStatus().toString();
    }

    @Override
    public FriendshipDto sendInvite(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("No user found with id " + senderId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("No user found with id " + receiverId));

        Friendship friendship = new Friendship();
        friendship.setSender(sender);
        friendship.setReceiver(receiver);
        friendship.setStatus(FriendshipStatus.REQUESTED);

        Friendship savedFriendship = friendshipRepository.save(friendship);

        return FriendshipMapper.mapToFriendshipDto(savedFriendship);
    }

    @Override
    public FriendshipDto acceptInvite(Long senderId, Long receiverId) {
        Friendship friendship = friendshipRepository.findFriendshipByUsers(senderId, receiverId);

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        Friendship updatedFriendship = friendshipRepository.save(friendship);

        return FriendshipMapper.mapToFriendshipDto(updatedFriendship);
    }
    @Override
    public void deleteFriendship(Long senderId, Long receiverId) {
        Friendship friendship = friendshipRepository.findFriendshipByUsers(senderId, receiverId);
        friendshipRepository.delete(friendship);
    }
}