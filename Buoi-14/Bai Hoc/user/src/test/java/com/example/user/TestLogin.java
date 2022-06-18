package com.example.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import com.example.user.model.UserModel;
import com.example.user.service.UserService;

@SpringBootTest
public class TestLogin {
    @Autowired private UserService userService;
    @Test
    public void addUser(){
        UserModel user =  userService.addUser("fullName", "email@gmail.com", "123444");
        assertThat(user).isNotNull();
    }

}
