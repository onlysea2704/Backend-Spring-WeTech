package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, Long> {
    Optional<Form> findByCode(String code);
}
