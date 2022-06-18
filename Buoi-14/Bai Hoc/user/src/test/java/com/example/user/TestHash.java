package com.example.user;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.example.user.hash.Hashing;

@SpringBootTest
public class TestHash {
    @Autowired Hashing hashing;
    @Test
    public void hashPassword(){
        var passwords = List.of("abc","12345@","019231409124");
        for (String password : passwords) {
            String hassedPass = hashing.hashPassword(password);
            assertThat(hassedPass).isNotNull();
            System.out.println(hassedPass);
        }
    }

    @Test
    public void validatePassword(){
        var passwords = List.of("abc","12345@","019231409124");
        for (String password : passwords) {
            String hassedPass = hashing.hashPassword(password);
            assertThat(hashing.validatePassword(password, hassedPass)).isTrue();
        }

        
    }
}
