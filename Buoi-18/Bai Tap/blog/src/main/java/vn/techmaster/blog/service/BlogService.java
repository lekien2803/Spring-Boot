package vn.techmaster.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.dto.CommentInfo;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.repository.CommentRepository;
import vn.techmaster.blog.request.BlogRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
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

    public List<CommentInfo> getCommentsByBlogId(String id){
        return commentRepository.getCommentsByBlogId(id);
    }

    //Lay danh sahc tat ca blog o dang DTO
    public List<BlogDto> getAllBlogDto(){
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    // Lay danh sach tat ca blog o dang DTO cua user
    public List<BlogDto> getAllBlogDtoByUserId(Integer id){
        List<Blog> blogs = blogRepository.getBlogsByUserId(id);
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

//    public BlogDto createBlog(BlogRequest blogRequest){
//
//    }
}
