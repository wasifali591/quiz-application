package in.theexplorers.quiz.services.impl;

import in.theexplorers.quiz.dtos.common.UserDto;
import in.theexplorers.quiz.entities.User;
import in.theexplorers.quiz.entities.enums.UserRole;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.UserRepository;
import in.theexplorers.quiz.services.UserService;
import in.theexplorers.quiz.utilities.converters.UserConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userConverter::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userConverter.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userConverter.userDtoToUser(userDto);
        user = userRepository.save(user);
        return userConverter.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(UserRole.valueOf(userDto.getRole()));
        user = userRepository.save(user);

        return userConverter.userToUserDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

