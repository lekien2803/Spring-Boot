package com.example.exercise.request;

import com.example.exercise.entity.Topic;
import com.example.exercise.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    private Integer id;
    private String name;
    private String slug;
    private int type;
    private String description;
    private String thumbnail;
    private User supporter;
    private List<Topic> topics;
    private MultipartFile image;
}
