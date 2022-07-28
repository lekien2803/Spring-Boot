package com.example.exercise.repository;

import com.example.exercise.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {


    List<Topic> getByIdIn(List<Integer> Ids);
}