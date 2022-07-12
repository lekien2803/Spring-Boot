package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.dto.UserDto;
import vn.techmaster.blog.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select new vn.techmaster.blog.dto.UserDto(u.id, u.name, u.email, u.avatar) from User u where u.id = :id")
    UserDto getUserDto(@Param("id") Integer id);

//    @Query("select new vn.techmaster.blog.dto.UserDto(u.id, u.name, u.email, u.avatar) from User u where u.id = ?1")
//    UserDto getUserDto(Integer id);

    <T> T getUserById(Integer id, Class<T> type);

}