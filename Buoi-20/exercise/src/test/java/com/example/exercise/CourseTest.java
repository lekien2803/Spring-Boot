package com.example.exercise;

import com.example.exercise.entity.Course;
import com.example.exercise.entity.Topic;
import com.example.exercise.request.CourseRequest;
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
    void createCourse(){
        
        Course course = new Course(100, "test Course create", "", 1, "test Course createtest Course createtest Course createtest Course createtest Course create", "", null, null);
        courseService.createCrouse(course);
    }

}
