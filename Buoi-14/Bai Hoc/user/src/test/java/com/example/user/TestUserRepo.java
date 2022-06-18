package com.example.user;

import org.junit.jupiter.api.Test;

import com.example.user.model.State;
import com.example.user.model.UserModel;
import com.example.user.repository.UserRepo;
import static org.assertj.core.api.Assertions.*;

public class TestUserRepo {
    @Test
    public void addUser(){
        UserRepo userRepo = new UserRepo();
        UserModel user =  userRepo.addUser("Abc", "abc@gmail.com", "TM0001",State.PENDING);
         assertThat(user).isNotNull();
         System.out.println("id=" + user.getId());
         assertThat(user.getId()).isNotNull();
         assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void isEmailExist(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("Abc", "abc@gmail.com", "TM0001",State.PENDING);
        assertThat(userRepo.isEmailExist("abc@gmail.com")).isTrue();
    }

    @Test
    public void findEmail(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("Abc", "abc@gmail.com", "TM0001",State.PENDING);
        userRepo.addUser("acbc1", "acbc1@gmail.com", "TM0001",State.PENDING);
        userRepo.addUser("abc2", "abc2@gmail.com", "TM0001",State.PENDING);
        assertThat(userRepo.findEmail("abc2@gmail.com")).isPresent();
    }

}
