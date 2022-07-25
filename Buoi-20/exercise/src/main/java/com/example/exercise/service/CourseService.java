package com.example.exercise.service;

import com.example.exercise.entity.Course;
import com.example.exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Page<Course> findAllPaging(int page, int pageSize){
        return courseRepository.findAll(PageRequest.of(page,pageSize));
    };

    public List<Course> getCoursesOnlab(){
        return courseRepository.getCoursesOnlab();
    }
}
