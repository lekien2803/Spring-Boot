package com.example.exercise;

import com.example.exercise.entity.Course;
import com.example.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseService courseService;

    @Test
    void findByName() {
        Page<Course> courses =courseService.getByNameContainsIgnoreCase("eos", PageRequest.of(0,6));
        System.out.println(courses);
    }
}
