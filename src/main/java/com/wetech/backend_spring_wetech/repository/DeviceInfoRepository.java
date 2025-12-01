package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Long>{
    DeviceInfo findDeviceInfoByUserIdAndUserAgentAndScreenAndCpuCoresAndRamAndGpuAndPlatform(Long userId, String userAgent, String screen, Integer cpuCores, String ram, String gpu, String platform);

    List<DeviceInfo> findDeviceInfoByUserId(Long userId);
}