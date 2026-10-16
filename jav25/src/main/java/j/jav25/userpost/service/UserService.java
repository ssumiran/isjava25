package j.jav25.userpost.service;

import java.util.List;

import j.jav25.userpost.dto.UserDto;

public interface UserService {
	UserDto addUser(UserDto userDto);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);
}
