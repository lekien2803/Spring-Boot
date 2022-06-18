package com.example.user.service;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.example.user.hash.Hashing;
import com.example.user.model.State;
import com.example.user.model.UserModel;
import com.example.user.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplnMemory implements UserService {
    private UserRepo userRepo;
    private Hashing hashing;

    @Override
    public UserModel addActiveUser(String fullName, String email, String password) {
        // TODO Auto-generated method stub
        return userRepo.addUser(fullName, email, hashing.hashPassword(password), State.ACTIVE);
    }

    @Override
    public UserModel addUser(String fullName, String email, String password) {
        // TODO Auto-generated method stub
        return userRepo.addPendingUser(fullName, email, hashing.hashPassword(password));
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserModel findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserModel login(String email, String password) {
        Optional<UserModel> oUser = userRepo.findEmail(email);
        if (!oUser.isPresent()) {
            return null;
        }
        UserModel user = oUser.get();
        // validate password
        return hashing.validatePassword(password, user.getHashedPassword()) ? user : null;
    }

    @Override
    public Boolean logout(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean activateUser(String activation_code) {
        // TODO Auto-generated method stub
        return null;
    }

}
