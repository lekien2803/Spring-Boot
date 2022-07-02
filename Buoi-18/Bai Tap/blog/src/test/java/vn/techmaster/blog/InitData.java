package vn.techmaster.blog;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.entity.IdentityCard;
import vn.techmaster.blog.entity.User;
import vn.techmaster.blog.repository.*;

import java.util.Random;

@SpringBootTest
public class InitData {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IdentityCardRepository identityCardRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Faker faker;
    @Autowired
    private Slugify slugify;
    @Autowired
    private Random random;

    @Test
    void save_user_and_identity() {
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email("https://" + faker.internet().emailAddress())
                    .password(faker.number().digits(3))
                    .identityCard(new IdentityCard())
                    .build();

            userRepository.save(user);
        }
    }
}
