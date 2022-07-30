package com.example.exercise.repository;

import com.example.exercise.dto.CoursesInfo;
import com.example.exercise.entity.Course;
import com.example.exercise.entity.Topic;
import com.example.exercise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> getByType(int type, Pageable pageable);

//    Optional<Course> findById(Integer integer);



    Page<Course> getByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<Course> getByNameContainsIgnoreCaseAndTopics_Id(String name, Integer TopicId, Pageable pageable);

    Page<Course> getByNameContainsIgnoreCaseAndTopics_NameContainsIgnoreCase(String name, String topicName, Pageable pageable);

    Page<Course> getByNameContainsIgnoreCaseAndTopics_Slug(String name, String slug, Pageable pageable);

    Page<Course> getByNameContainsIgnoreCaseAndTypeAndTopics_Slug(String name, int type, String slug, Pageable pageable);

    Page<Course> getByNameContainsIgnoreCaseAndType(String name, int type, Pageable pageable);







}