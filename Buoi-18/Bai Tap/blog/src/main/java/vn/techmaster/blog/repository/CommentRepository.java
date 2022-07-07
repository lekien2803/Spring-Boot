package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.dto.CommentInfo;
import vn.techmaster.blog.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c where c.blog.id = ?1")
    List<CommentInfo> getCommentsByBlogId(String id);
}