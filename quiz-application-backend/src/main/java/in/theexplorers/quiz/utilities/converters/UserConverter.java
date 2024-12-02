package in.theexplorers.quiz.utilities.converters;

import in.theexplorers.quiz.dtos.common.UserDto;
import in.theexplorers.quiz.entities.User;

public interface UserConverter {
    UserDto userToUserDto(User user);


    User userDtoToUser(UserDto userDto);


    User toUpdateUser(UserDto userDto, User user);
}
