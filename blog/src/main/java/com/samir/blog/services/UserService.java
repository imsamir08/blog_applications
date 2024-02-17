package com.samir.blog.services;

import com.samir.blog.entities.User;
import com.samir.blog.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer UserId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
