package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.*;
import javax.persistence.Table;

import com.example.demo.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(name = "userInfo", classes = @ConstructorResult(targetClass = UserDto.class, columns = {
        @ColumnResult(name = "id"),
        @ColumnResult(name = "name"),
        @ColumnResult(name = "email")
}))
@NamedNativeQuery(name = "getUserInfo", resultSetMapping = "userInfo", query = "SELECT user.id, user.name, user.email "
        +
        "FROM user " +
        "WHERE user.email = ?1")
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password", columnDefinition = "varchar(255) default '111'")
    private String password;
}
