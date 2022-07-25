package com.example.exercise.service;

import com.example.exercise.entity.Course;
import com.example.exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Page<Course> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1,10);
        return courseRepository.findAll(pageable);
    }
}
