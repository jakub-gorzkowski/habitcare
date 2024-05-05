package io.habitcare.web.service.user;

import io.habitcare.web.dto.UserDto;
import io.habitcare.web.mapper.UserMapper;
import io.habitcare.web.model.User;
import io.habitcare.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.habitcare.web.mapper.UserMapper.mapToUserDto;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public List<UserDto> findUserByUsername(String username) {
        List<User> users = userRepository.findByUsernameContaining(username);
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }
}
