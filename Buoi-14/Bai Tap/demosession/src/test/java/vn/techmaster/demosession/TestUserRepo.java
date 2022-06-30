package vn.techmaster.demosession;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.demosession.model.State;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.repository.UserRepo;

@SpringBootTest
public class TestUserRepo {
    @Autowired private UserRepo userRepo;
    @Test
    void addUserTest(){
        User user = userRepo.addUser("le kien", "abv@gmail.com", State.PENDING, "abc");
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

    @Test
    void addUserPendingTest(){
        User user = userRepo.addUserPending("le kien", "abv@gmail.com", "abc");
        assertThat(user).isNotNull();
        System.out.println(user.getState());
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    void isEmailExistTest(){
        User user = userRepo.addUserPending("le kien", "abv@gmail.com", "abc");
        User user1 = userRepo.addUserPending("le hieu", "abacsasc@gmail.com", "abc");
        User user2 = userRepo.addUserPending("duc minh", "abc@gmail.com", "abc");

        assertThat(userRepo.isEmailExist("abv@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("abacsasc@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("abc@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("abvasas@gmail.com")).isFalse();
    }

    @Test
    void findByEmailTest(){
        User user = userRepo.addUserPending("le kien", "abv@gmail.com", "abc");
        User user1 = userRepo.addUserPending("le hieu", "abacsasc@gmail.com", "abc");
        User user2 = userRepo.addUserPending("duc minh", "abc@gmail.com", "abc");

        assertThat(userRepo.findByEmail("abv@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("abacsasc@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("abc@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("abvasdasd@gmail.com")).isNotPresent();
    }
}
