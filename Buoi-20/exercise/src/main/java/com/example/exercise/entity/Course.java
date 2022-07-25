package com.example.exercise.entity;

import com.example.exercise.dto.CoursesInfo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SqlResultSetMapping(
        name = "listCoursesOnlab",
        classes = @ConstructorResult(
                targetClass = CoursesInfo.class,
                columns = {
                        @ColumnResult(name = "id", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "slug", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "thumbnail", type = String.class),
                        @ColumnResult(name = "supporterInfo", type = String.class),
                }
        )
)
@NamedNativeQuery(
        name = "getCoursesOnlab",
        resultSetMapping = "listCoursesOnlab",
        query = "SELECT c.id , c.description , c.name , c.slug , c.thumbnail , c.`type` , t.name,\n" +
                "\t\tjson_object('id', u.id, 'name', u.name, 'phone', u.phone, 'avatar', u.avatar, \"email\", u.email)\n" +
                "FROM course c \n" +
                "left join course_topics ct on c.id = ct.course_id \n" +
                "LEFT join topic t on t.id = ct.topics_id \n" +
                "LEFT JOIN `user` u ON c.supporter_id = u.id \n" +
                "WHERE c.`type` = 0\n" +
                "LIMIT 6"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "type",columnDefinition = "int default 0")
    private int type;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supporter_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "course_topics",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "topics_id"))
    private List<Topic> topics = new ArrayList<>();


    @PreRemove
    public void preRemove() {
        this.topics = null;
    }
}