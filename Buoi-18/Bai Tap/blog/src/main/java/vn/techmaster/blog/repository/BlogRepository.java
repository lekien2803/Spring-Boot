package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.dto.BlogInfoBySomething;
import vn.techmaster.blog.entity.Blog;


import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    @Query(name = "getAllBlogInfo", nativeQuery = true)
    List<BlogInfo> getAllBlogInfo();
    @Query(name = "getBlogsByCategoryName", nativeQuery = true)
    List<BlogInfoBySomething> getBlogsByCategoryName(@Param("name") String categoryName);
    List<Blog> getBlogsByUserId(Integer id);

    @Query(name = "getBlogsByUserName", nativeQuery = true)
    List<BlogInfoBySomething> getBlogsByUserName(@Param("name") String userName);

    Blog getBlogById(String id);


}