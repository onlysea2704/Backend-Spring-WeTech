package com.wetech.backend_spring_wetech.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "device_info")
@Data
public class DeviceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")                // khóa chính
    private Long id;
    @Column(name = "user_id")           // ID user
    private Long userId;
    @Column(name = "user_agent")        // Chuỗi user-agent
    private String userAgent;
    @Column(name = "screen")            // Kích thước màn hình
    private String screen;
    @Column(name = "cpu_cores")         // Số lõi CPU
    private Integer cpuCores;
    @Column(name = "ram")               // RAM lưu dạng text ("8 GB")
    private String ram;
    @Column(name = "gpu")               // Tên GPU
    private String gpu;
    @Column(name = "platform")          // Win32 / MacIntel / Linux x86_64
    private String platform;
}
