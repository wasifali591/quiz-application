package in.theexplorers.quiz.utilities.converters.impl;

import in.theexplorers.quiz.dtos.common.UserDto;
import in.theexplorers.quiz.entities.User;
import in.theexplorers.quiz.utilities.converters.UserConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    private final ModelMapper modelMapper;

    public UserConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public UserDto userToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * @param userDto
     * @return
     */
    @Override
    public User userDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    /**
     * @param userDto
     * @param user
     * @return
     */
    @Override
    public User toUpdateUser(UserDto userDto, User user) {
        modelMapper.map(userDto, user);
        return user;
    }
}
