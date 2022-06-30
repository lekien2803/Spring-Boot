package vn.techmaster.demosession;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.demosession.hash.Hashing;

@SpringBootTest
public class TestHash {
    @Autowired
    private Hashing hashing;

    @Test
    void hashPasswordTest() {
        var passwords = List.of("abc", "qwfasfasf", "12o4oNODFno", "!@124af((");
        for (String password : passwords) {
            String hash = hashing.hashPassword(password);
            assertThat(hash).isNotNull();
        }
    }

    @Test
    void validatePasswordTest() {
        var passwords = List.of("abc", "qwf asfasf", "12o4oNODFno", "!@124af((");
        for (String password : passwords) {
            String hashed = hashing.hashPassword(password);
            System.out.println(hashed);
            // assertThat(hashing.validatePassword(password, hashed)).isTrue();
            // assertThat(hashed).isNotNull();
        }

        assertThat(hashing.validatePassword("abc", "1000:ba3db42bd5a5c651404f44410161bd4e:41539742b55ea8bd4af594c8efa810c751f97b8931214879a262b9813e329ea0928822dab9248d5c75dd83a4d1b106dd08405a962b7404f6fb09c52201ed1570")).isFalse();
    }
}
