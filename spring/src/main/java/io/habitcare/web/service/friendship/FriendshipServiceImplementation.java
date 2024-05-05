package io.habitcare.web.service.friendship;

import io.habitcare.web.dto.FriendshipDto;
import io.habitcare.web.dto.UserDto;
import io.habitcare.web.mapper.FriendshipMapper;
import io.habitcare.web.mapper.UserMapper;
import io.habitcare.web.model.Friendship;
import io.habitcare.web.model.FriendshipStatus;
import io.habitcare.web.model.User;
import io.habitcare.web.repository.FriendshipRepository;
import io.habitcare.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

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
    public List<UserDto> getAllFriends(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("No user found with id " + userId));
        List<Friendship> friendships = friendshipRepository.findAllAcceptedFriendships(user);
        List<UserDto> friends = new ArrayList<>();
        if (friendships.isEmpty()) {
            return friends;
        }
        for (Friendship friendship : friendships) {
            User friend = friendship.getSender().equals(user) ? friendship.getReceiver() : friendship.getSender();
            friends.add(UserMapper.mapToUserDto(friend));
        }
        return friends;
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
    public FriendshipDto acceptInvite(Long FriendshipId) {
        Friendship friendship = friendshipRepository.findById(FriendshipId)
                .orElseThrow(() -> new IllegalArgumentException("No friendship found with id " + FriendshipId));

        friendship.setStatus(FriendshipStatus.ACCEPTED);

        Friendship updatedFriendship = friendshipRepository.save(friendship);

        return FriendshipMapper.mapToFriendshipDto(updatedFriendship);
    }

    @Override
    public FriendshipDto declineInvite(Long FriendshipId) {
        Friendship friendship = friendshipRepository.findById(FriendshipId)
                .orElseThrow(() -> new IllegalArgumentException("No friendship found with id " + FriendshipId));

        friendship.setStatus(FriendshipStatus.DECLINED);

        Friendship updatedFriendship = friendshipRepository.save(friendship);

        return FriendshipMapper.mapToFriendshipDto(updatedFriendship);
    }

    @Override
    public FriendshipDto blockFriend(Long FriendshipId) {
        Friendship friendship = friendshipRepository.findById(FriendshipId)
                .orElseThrow(() -> new IllegalArgumentException("No friendship found with id " + FriendshipId));

        friendship.setStatus(FriendshipStatus.BLOCKED);

        Friendship updatedFriendship = friendshipRepository.save(friendship);

        return FriendshipMapper.mapToFriendshipDto(updatedFriendship);
    }
}