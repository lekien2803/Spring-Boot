package vn.techmaster.demosession;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.demosession.exception.UserException;
import vn.techmaster.demosession.model.State;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.repository.UserRepo;
import vn.techmaster.demosession.service.UserService;
import vn.techmaster.demosession.service.UserServiceImMemory;

@SpringBootTest
public class TestUserServiceInMemory {
    @Autowired
    private UserService userService;

    @Test
    void addUserTest() {
        assertThat(userService.addUser("Le Kien", "abc@gmail.com", "abc")).isNotNull();

    }

    @Test
    void login_when_account_is_pending_Test() {
        userService.addUser("Le Kien", "abc@gmail.com", "abc");
        assertThatThrownBy(() -> {
            userService.login("abc@gmail.com", "abc");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User is not active");
    }

    @Test
    void login_when_password_incorrect_Test() {
        userService.addUserThenAutoActivate("Le Kien", "abc@gmail.com", "abc");
        assertThatThrownBy(() -> {
            userService.login("abc@gmail.com", "abc1");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("Password incorrect");
    }

    @Test
    void login_when_account_is_notfound_Test() {
        userService.addUser("Le Kien", "abc@gmail.com", "abc");
        assertThatThrownBy(() -> {
            userService.login("abc1@gmail.com", "abc");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    void login_success(){
        userService.addUserThenAutoActivate("Le Kien", "abc@gmail.com", "abc");
        User user = userService.login("abc@gmail.com", "abc");
        assertThat(user).isNotNull();
    }
}
