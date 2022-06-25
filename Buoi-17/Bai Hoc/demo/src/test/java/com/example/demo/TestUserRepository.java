package com.example.demo;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class TestUserRepository {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUserTest() {
        User user = User.builder().name("Nguyen Van D").email("aasadadddddsd@gmaial.com").password("").build();
        User user1 = User.builder().name("Tran Van E").email("cascascasc@gmaial.com").build();
        User user2 = User.builder().name("Hoang Thi F").email("ccc123123123ccc@gmaial.com").build();

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void countByNameTest() {
        long count = userRepository.countByNameContainsIgnoreCase("le");
        Assertions.assertThat(count).isEqualTo(1L);
    }

    @Test
    void sortNameDescTest() {
        List<User> users = userRepository.findByOrderByNameDesc(Sort.by("name").descending());
        users.forEach(user -> System.out.println(user.getName()));

        Assertions.assertThat(users.get(0).getName()).isEqualTo("Le Kien");
    }

    @Test
    void findByOrderByNameAscTest() {
        // List<User> users = userRepository
    }

    @Test
    void findByName() {
        User user = userRepository.findByName("Le Kien");
        Assertions.assertThat(user).isNotNull();
        System.out.println(user);
    }

    @Test
    void getUsersByNameContainsIgnoreCaseTest() {
        List<User> users = userRepository.getUsersByNameContainsIgnoreCase("mememe");
        Assertions.assertThat(users).isEmpty();
    }

    @Test
    void findByEmailDtoTest() {
        UserDto userDto = userRepository.findByEmail("abc@gmail.com");
        Assertions.assertThat(userDto.getName()).isEqualTo("Le Kien");
    }
}
