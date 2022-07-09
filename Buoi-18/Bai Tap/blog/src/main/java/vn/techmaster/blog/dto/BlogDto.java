package vn.techmaster.blog.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private LocalDateTime createAt;
    private int status;
    private UserDto1 user;
    private List<CategoryDto> categories;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto1 implements Serializable {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryDto implements Serializable {
        private Integer id;
        private String name;
    }
}
