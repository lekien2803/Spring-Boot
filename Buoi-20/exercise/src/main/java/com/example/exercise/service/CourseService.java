package com.example.exercise.service;

import com.example.exercise.dto.CoursesInfo;
import com.example.exercise.entity.Course;
import com.example.exercise.entity.Topic;
import com.example.exercise.entity.User;
import com.example.exercise.exception.NotFoundException;
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



    public Page<Course> findAllPaging(int page, int pageSize, String name, String topicSlug){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (topicSlug.equals("")){
            return courseRepository.getByNameContainsIgnoreCase(name, pageable);
        }
        return courseRepository.getByNameContainsIgnoreCaseAndTopics_Slug(name, topicSlug, pageable);
    }



    public Page<Course> getByType (int type, Pageable pageable){
        return courseRepository.getByType(type, pageable);
    }

    public Page<Course> findAllPagingByType(int page, int pageSize, String name, int type, String slug){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (slug.equals("")) {
            return courseRepository.getByNameContainsIgnoreCaseAndType(name, type, pageable);
        }
        return  courseRepository.getByNameContainsIgnoreCaseAndTypeAndTopics_Slug(name, type, slug, pageable);
    }

    public User getUserByCourseId(Integer id){
        Optional<Course> course = courseRepository.findById(id);
        return course.get().getUser();
    }

    public Course getById(Integer id){
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course with id = \" + id + \" does not exist"));
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



    public Course createAndUpdate(Course course){
        course.setSlug(slugify.slugify(course.getName()));
        return courseRepository.save(course);
    }



    public CourseRequest toCourseRequest(Course course){
        return CourseRequest.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .thumbnail(course.getThumbnail())
                .topics(course.getTopics())
                .type(course.getType())
                .supporter(course.getUser())
                .slug(course.getSlug())
                .build();
    }

    public Course fromRequestToCourse (CourseRequest courseRequest){
        String thumbnail = courseRequest.getThumbnail();
        if (thumbnail == null){
            thumbnail = getById(courseRequest.getId()).getThumbnail();
        }

        return Course.builder()
                .id(courseRequest.getId())
                .name(courseRequest.getName())
                .slug(courseRequest.getSlug())
                .type(courseRequest.getType())
                .user(courseRequest.getSupporter())
                .thumbnail(thumbnail)
                .topics(courseRequest.getTopics())
                .description(courseRequest.getDescription())
                .build();
    }

    public void deleteCourse(Course course){
        courseRepository.delete(course);
    }

}
