package com.example.exercise.entity;

import com.example.exercise.dto.CoursesInfo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @ManyToOne
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