package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
