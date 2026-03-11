package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;     // Số nhà, đường, xóm, ấp, thôn
    private String ward;       // Xã/Phường/Đặc khu
    private String province;   // Tỉnh/Thành phố
}
