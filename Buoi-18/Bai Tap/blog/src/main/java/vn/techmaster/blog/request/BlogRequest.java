package vn.techmaster.blog.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogRequest {
    private String title;
    private String description;
    private String content;
    private int status;
    private List<Integer> categories;
    private String thumbnail;
}
