package io.habitcare.web.service.user;

import io.habitcare.web.dto.UserDto;
import io.habitcare.web.mapper.UserMapper;
import io.habitcare.web.model.Friendship;
import io.habitcare.web.model.Habit;
import io.habitcare.web.model.User;
import io.habitcare.web.repository.FriendshipRepository;
import io.habitcare.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.habitcare.web.mapper.UserMapper.mapToUserDto;


@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public boolean exists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        user.setId(userId);
        return userRepository.findById(userId).map(existingUser -> {
            Optional.ofNullable(user.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(user.getRealUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(user.getImageUrl()).ifPresent(existingUser::setImageUrl);
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public void updateUsername(Long userId, String username) {
        userRepository.findById(userId).map(existingUser -> {
            existingUser.setUsername(username);
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public void updateImageUrl(Long userId, String imageUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        user.setImageUrl(imageUrl);
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        return mapToUserDto(user);
    }

    @Override
    public User findUserDetailsById(Long userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }

    @Override
    public List<UserDto> findUserByUsername(String username) {
        List<User> users = userRepository.findByUsernameContaining(username);
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public List<Habit> findUserHabits(Long userId) {
        List<Habit> habits = userRepository.findUserHabits(userId);
        return habits;
    }

    @Override
    public List<UserDto> getAllFriends(Long userId) {
        List<Friendship> friendships = friendshipRepository.findAllAcceptedFriendships(userId);
        List<UserDto> friends = new ArrayList<>();
        if (friendships.isEmpty()) {
            return friends;
        }
        for (Friendship friendship : friendships) {
            User friend = friendship.getSender().getId().equals(userId) ? friendship.getReceiver() : friendship.getSender();
            friends.add(UserMapper.mapToUserDto(friend));
        }
        return friends;
    }
    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.GetUserIdByEmail(email);
    }

    @Override
    public List<UserDto> getAllIvitations(Long userId) {
        List<Friendship> friendships = friendshipRepository.findAllRequestedFriendships(userId);
        List<UserDto> invites = new ArrayList<>();
        if (friendships.isEmpty()) {
            return invites;
        }
        for (Friendship friendship : friendships) {
            User sender = friendship.getSender();
            invites.add(UserMapper.mapToUserDto(sender));
        }
        return invites;
    }
}
