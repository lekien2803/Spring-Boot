package com.example.exercise.repository;

import com.example.exercise.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(name = "getCoursesOnlab" , nativeQuery = true)
    List<Course> getCoursesOnlab();
}