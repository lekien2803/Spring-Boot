package vn.techmaster.blog.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUpdateRequest extends BlogRequest{
    private String slug;
    public BlogUpdateRequest(String title, String slug, String description, String content, int status, List<Integer> categories, String thumbnail) {
        super(title, description, content, status, categories, thumbnail);
        this.slug = slug;
    }
}
