package com.example.Pranay.controller;

import com.example.Pranay.entity.User;
import com.example.Pranay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
//    @Autowired
//    UserService userService;
//
//    // CREATE USER
//    @PostMapping
//    public User createUser(@RequestBody User user){
//        return userService.createUser(user);
//    }
//
//    // GET ALL USERS
//    @GetMapping
//    public List<User> getUsers(){
//        return userService.getUsers();
//    }
//
//    // GET USER BY ID
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable long id){
//        return userService.getuser(id);
//    }
//
//    // UPDATE USER
//    @PutMapping
//    public User updateUser(@RequestBody User user){
//        return userService.updateUser(user);
//    }
//
//    // DELETE USER
//    @DeleteMapping("/{id}")
//    public User deleteUser(@PathVariable long id){
//        return userService.deleteUser(id);
//    }
}
