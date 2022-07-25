package com.example.exercise;

import com.example.exercise.entity.Course;
import com.example.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseService courseService;

    @Test
    void course_test() {
        Page<Course> page = courseService.findPage(1);
        int totalPage = page.getTotalPages();
        long totalItem = page.getTotalElements();
        List<Course> courses = page.getContent();
        courses.forEach(course -> {
            System.out.println(course.toString());
        });
    }
}
