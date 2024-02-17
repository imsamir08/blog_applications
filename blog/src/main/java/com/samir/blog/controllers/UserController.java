package com.samir.blog.controllers;

import com.samir.blog.payloads.ApiResponse;
import com.samir.blog.payloads.UserDto;
import com.samir.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

//    POST - create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
//    PUT - update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updateUser=userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updateUser);
    }
//    GET - get AllUser
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

//    GET - get AllUser
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

//    DELETE - delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
    }
}
