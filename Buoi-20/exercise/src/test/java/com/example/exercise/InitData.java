package com.example.exercise;

import com.example.exercise.entity.Course;
import com.example.exercise.entity.Image;
import com.example.exercise.entity.Topic;
import com.example.exercise.entity.User;
import com.example.exercise.repository.CourseRepository;
import com.example.exercise.repository.ImageRepository;
import com.example.exercise.repository.TopicRepository;
import com.example.exercise.repository.UserRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class InitData {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Autowired
    private Random rd;

    @Test
    void save_user() {
        for (int i = 0; i < 50; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().cellPhone())
                    .avatar(faker.company().logo())
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_image() {
        for (int i = 0; i < 50; i++) {
            Image image = Image.builder()
                    .link(faker.company().logo())
                    .build();
            imageRepository.save(image);
        }
    }

    @Test
    void save_topic() {
        for (int i = 0; i < 20; i++) {
            Topic topic = Topic.builder()
                    .name(faker.gameOfThrones().character())
                    .build();
            topicRepository.save(topic);
        }
    }

    @Test
    void save_courses() {
        List<User> users = userRepository.findAll();
        List<Topic> topics = topicRepository.findAll();

        for (int i = 0; i < 50; i++) {
            User userRd = users.get(rd.nextInt(users.size()));
            List<Image> images = imageRepository.findAll();
            String imageRd = images.get(rd.nextInt(images.size())).getLink();

            List<Topic> topicsRd = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                Topic topicRd = topics.get(rd.nextInt(topics.size()));
                if (!topicsRd.contains(topicRd)){
                    topicsRd.add(topicRd);
                }
            }

            String name = faker.lorem().sentence(10);
            Course course = Course.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().sentence(50))
                    .thumbnail(imageRd)
                    .topics(topicsRd)
                    .type(rd.nextInt(2))
                    .user(userRd)
                    .build();
            courseRepository.save(course);
        }

    }
}
