package com.example.exercise.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class CoursesInfo {
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private String thumbnail;
    private SupporterInfo supporterInfo;

    public CoursesInfo(Integer id, String name, String slug, String description, String thumbnail, String supporterInfo) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.thumbnail = thumbnail;

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            this.supporterInfo = objectMapper.readValue(supporterInfo, SupporterInfo.class);
        }catch (JsonProcessingException e){
            this.supporterInfo = null;
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class SupporterInfo{
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
}
