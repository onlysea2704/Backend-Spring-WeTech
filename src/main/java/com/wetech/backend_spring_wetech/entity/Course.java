package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "real_price")
    private Double realPrice;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "type_course")
    private String typeCourse;

    @Column(name = "link_image")
    private String linkImage;

    @Column(name = "intro_1")
    private String intro1;

    @Column(name = "intro_2")
    private String intro2;

    @Column(name = "number_register")
    private Integer numberRegister;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Section> sections;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<MyCourse> myCourses;
}
