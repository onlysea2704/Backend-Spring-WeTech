package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_searchs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearch {
    @Id
    @Column(name = "cccd")
    private String cccd;   // căn cước công dân (PK)

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private String dob; // Có thể đổi sang LocalDate nếu format chuẩn
}
