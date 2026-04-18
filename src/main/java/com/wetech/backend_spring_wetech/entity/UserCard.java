package com.wetech.backend_spring_wetech.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String cccd; // căn cước công dân (PK)

    private String gender;

    private String dob; // Có thể đổi sang LocalDate nếu format chuẩn

    private String nationality;

    private String ethnicity; // dan toc

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id")
    private Address permanentAddress; // dia chi thuong chu

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_address_id")
    private Address currentAddress; // noi o hien tai

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
