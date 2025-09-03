package com.wetech.backend_spring_wetech.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "my_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_course_id")
    private Long myCourseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "course_id")
    private Course course;
}

