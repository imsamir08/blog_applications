package com.samir.blog.services.impl;

import com.samir.blog.entities.User;
import com.samir.blog.exceptions.ResourceNotFoundException;
import com.samir.blog.payloads.UserDto;
import com.samir.blog.repositories.UserRepo;
import com.samir.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
//    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=dtoToUser(userDto);
        User savedUser=userRepo.save(user);
        return UserToDto(savedUser);
    }
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser=userRepo.save(user);
        return UserToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
        return UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        System.out.println("Getting users.");
        List<User> users=userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> UserToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        userRepo.delete(user);
    }
    public User dtoToUser(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto UserToDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
