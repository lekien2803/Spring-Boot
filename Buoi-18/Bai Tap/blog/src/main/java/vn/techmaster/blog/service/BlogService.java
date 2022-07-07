package vn.techmaster.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.dto.CommentInfo;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public List<BlogInfo> getAllBlogInfo(){
        return blogRepository.getAllBlogInfo();
    }

    public List<BlogInfo> getBlogPopular(int limit){
        return blogRepository.getAllBlogInfo().stream()
                .sorted((a,b) -> b.getCountComment() - a.getCountComment())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Category> getCategoriesPopular(int limit){
        return categoryRepository.getCategoriesPopular(limit);
    }

    public BlogInfo getBlogInfoById(String id){
        Optional<BlogInfo> blogInfoOptinal = blogRepository.getAllBlogInfo().stream()
                .filter(blogInfo -> blogInfo.getId().equals(id))
                .findFirst();
        return blogInfoOptinal.orElse(null);
    }

    public CommentInfo getCommentInfo(){

    }
}
