package vn.techmaster.blog;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.request.BlogRequest;
import vn.techmaster.blog.service.BlogService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class BlogTest {

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Autowired
    private Random rd;


//    @Test
//    void createBlog() {
//        List<Category> categories = categoryRepository.findAll();
//        List<Category> categoriesRd = new ArrayList<>();
//        for (int j = 0; j < 3; j++) {
//            Category categoryRd = categories.get(rd.nextInt(categories.size()));
//            if(!categoriesRd.contains(categoryRd)) {
//                categoriesRd.add(categoryRd);
//            }
//        }
//        BlogRequest blogRequest = BlogRequest.builder()
//                .title("blogtest")
//                .categories(categoriesRd)
//                .status(1)
//                .content("blogtest123")
//                .description("this is blog test")
//                .thumbnail("fa")
//                .build();
//        blogService.createBlog(blogRequest);
//    }

    @Test
    void getAllBlog() {
      List<Blog> blogs = blogRepository.findAll();
        for (Blog b :
                blogs) {
            System.out.println(b);
        }
    }

}
