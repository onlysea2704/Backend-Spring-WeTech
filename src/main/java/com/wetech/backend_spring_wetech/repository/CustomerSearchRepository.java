package com.wetech.backend_spring_wetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSearchRepository extends JpaRepository<CustomerRepository, String> {
}

