package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}

