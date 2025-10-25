package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
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

    @Column(name = "intro_1", columnDefinition = "TEXT")
    private String intro1;

    @Column(name = "intro_2", columnDefinition = "TEXT")
    private String intro2;

    @Column(name = "number_register")
    private Integer numberRegister;

    @Column(name = "created_at")
    private Date createdAt;
}
