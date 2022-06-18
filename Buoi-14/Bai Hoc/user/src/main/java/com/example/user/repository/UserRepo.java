package com.example.user.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.user.model.State;
import com.example.user.model.UserModel;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String,UserModel> users = new ConcurrentHashMap<>();
    public UserModel addUser(String fullName, String email, String hashedPassword, State state){
        String id = UUID.randomUUID().toString();
        UserModel user = UserModel.builder()
        .id(id)
        .fullName(fullName)
        .email(email)
        .hashedPassword(hashedPassword)
        .state(state)
        .build();
        users.put(id, user);
        return user;
        
    }

    public UserModel addPendingUser(String fullName, String email, String hashedPassword){
        return addUser(fullName, email, hashedPassword, State.PENDING);
    }

    public boolean isEmailExist(String email){
        return users.values().stream()
        .filter(user -> user.getEmail().equalsIgnoreCase(email)).count() > 0;
    }

    public Optional<UserModel> findEmail(String email){

        return users.values().stream().filter(user->user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}
