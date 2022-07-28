package com.example.exercise.request;

import com.example.exercise.entity.Topic;
import com.example.exercise.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    private String name;
    private int type;
    private String description;
    private String thumbnail;
    private User user;
    private List<Integer> topics;
}
