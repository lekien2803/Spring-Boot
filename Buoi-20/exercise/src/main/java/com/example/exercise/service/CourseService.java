package com.example.exercise.service;

import com.example.exercise.dto.CoursesInfo;
import com.example.exercise.entity.Course;
import com.example.exercise.entity.User;
import com.example.exercise.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<Course> findAllPaging(int page, int pageSize, String name, Integer topicId){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (topicId == null){
            return getByNameContainsIgnoreCase(name, pageable);
        }
        return courseRepository.getByNameContainsIgnoreCaseAndTopics_Id(name, topicId, pageable);
    };

    public Page<Course> getByType (int type, Pageable pageable){
        return courseRepository.getByType(type, pageable);
    }

    public User getUserByCourseId(Integer id){
        Optional<Course> course = courseRepository.findById(id);
        return course.get().getUser();
    }

    public Optional<Course> findById(Integer id){
        return courseRepository.findById(id);
    }

    public Page<Course> getByNameContainsIgnoreCase(String name, Pageable pageable){
        return courseRepository.getByNameContainsIgnoreCase(name, pageable);
    }


}
