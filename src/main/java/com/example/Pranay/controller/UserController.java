package com.example.Pranay.controller;

import com.example.Pranay.dto.UserDto;
import com.example.Pranay.dto.UserResponseDto;
import com.example.Pranay.entity.User;
import com.example.Pranay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserResponseDto> getAllUsers(){
        return userService.getUsers()   ;
    }

    @PostMapping("")
    public User createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto) ;
    }
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable long id){
        return userService.getUser(id) ;
    }

    @PutMapping("")
    public UserResponseDto UpdateUser(@RequestBody UserDto userDto){
        return userService.updateUserPartial(userDto) ;
    }

    @DeleteMapping("/{id}")
    public UserResponseDto deleteUser(@PathVariable long id){
        return userService.deleteUser(id) ;
    }



}
