package com.example.exercise.service;

import com.example.exercise.dto.CoursesInfo;
import com.example.exercise.entity.Course;
import com.example.exercise.entity.Topic;
import com.example.exercise.entity.User;
import com.example.exercise.repository.CourseRepository;
import com.example.exercise.repository.UserRepository;
import com.example.exercise.request.CourseRequest;
import com.github.slugify.Slugify;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicService topicService;
    @Autowired
    private Slugify slugify;


    public Page<Course> findAllPaging(int page, int pageSize, String name, Integer topicId){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (topicId == null){
            return courseRepository.getByNameContainsIgnoreCase(name, pageable);
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

    public Page<Course> getByNameContainsIgnoreCase(int page, int pageSize, String name ){
        Pageable pageable = PageRequest.of(page, pageSize);
        return courseRepository.getByNameContainsIgnoreCase(name, pageable);
    }


    //Dashboard
    public Page<Course> findAllDashboard(int page, int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        return courseRepository.findAll(pageable);
    }

    public String showTopicsDashboard(Course course){
        return String.join(", ", course.getTopics().stream().map(Topic::getName).toList());
    }

//    public Course createCourse(CourseRequest courseRequest){
//
//        List<Topic> topics = topicService.getByIdIn(courseRequest.getTopics());
//
//        Course course = Course.builder()
//                .name(courseRequest.getName())
//                .description(courseRequest.getDescription())
//                .topics(topics)
//                .slug(slugify.slugify(courseRequest.getName()))
//                .thumbnail(courseRequest.getThumbnail())
//                .type(courseRequest.getType())
//                .user(courseRequest.getUser())
//                .build();
//
//        courseRepository.save(course);
//        return course;
//    }

    public Course createCrouse(Course course){
        course.setSlug(slugify.slugify(course.getName()));
        return courseRepository.save(course);
    }

}
