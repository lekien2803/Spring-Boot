package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.dto.UserDto;
import com.example.user.request.CreateUser;
import com.example.user.request.UpdatePassword;
import com.example.user.request.UpdateUserRequest;
import com.example.user.service.UserService;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUser() {
        return userService.getUsers();
    }

    // @GetMapping("/")
    // public String getHome(){
    // return "index";
    // }

    @GetMapping("/users/search")
    public List<UserDto> searchUser(@RequestParam String name) {

        return userService.getUser(name);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUser request) {

        return userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable("id") int id) {

        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable("id") int id, @RequestBody UpdateUserRequest request) {

        return userService.updateUser(id, request);
    }

    @PutMapping("/users/{id}/update-password")
    public void updatePassword(@PathVariable int id, @RequestBody UpdatePassword request) {

        userService.updatePassword(id, request);
    }

    @PostMapping("/users/{id}/forgot-password")
    public String forgotPassword(@PathVariable("id") int id) {
        return userService.forgotPassword(id);
    }
}
