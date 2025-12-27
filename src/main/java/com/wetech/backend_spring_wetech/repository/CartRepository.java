package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Cart;
import com.wetech.backend_spring_wetech.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findFirstByUserIdAndCourseId(Long userId, Long courseId);

    List<Cart> findByUserId(Long userId);
}
