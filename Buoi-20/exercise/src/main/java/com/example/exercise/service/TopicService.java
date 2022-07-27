package com.example.exercise.service;

import com.example.exercise.entity.Topic;
import com.example.exercise.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }
 }
