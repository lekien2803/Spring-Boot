package vn.techmaster.blog.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlogInfoBySomething {
    private String id;
    private String title;
    private String slug;
    private String description;
    private String thumbnail;
    private String publishedAt;
    private Integer countComment;
    private AuthorInfo authorInfo;
    private String something;


    public BlogInfoBySomething(String id, String title, String slug, String description, String thumbnail, String publishedAt, Integer countComment, String authorInfo, String something) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.thumbnail = thumbnail;
        this.publishedAt = publishedAt;
        this.countComment = countComment;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.authorInfo = objectMapper.readValue(authorInfo, AuthorInfo.class);
        } catch (JsonProcessingException e) {
            this.authorInfo = null;
        }
        this.something = something;
    }
}