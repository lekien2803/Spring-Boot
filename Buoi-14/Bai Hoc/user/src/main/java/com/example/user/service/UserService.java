package com.example.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.user.model.UserModel;

@Service
public interface UserService {
    public UserModel login(String email, String password);
    public Boolean logout(String email);
    //
    public UserModel addUser(String fullName, String email, String password);
    public UserModel addActiveUser(String fullName, String email, String password);
    public Boolean activateUser(String activation_code);
    //
    public Boolean updatePassword(String email, String password);
    public Boolean updateEmail(String email, String newEmail);
    //
    public Optional<UserModel> findByEmail(String email);
    public UserModel findById(String id);
}
