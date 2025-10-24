package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("""
        SELECT COUNT(u)
        FROM User u
    """)
    Long getTotalCustomers();

    @Query("""
        SELECT COUNT(u)
        FROM User u
        WHERE MONTH(u.created) = :month AND YEAR(u.created) = :year
    """)
    Long getNewCustomersThisMonth(@Param("month") int month, @Param("year") int year);

    @Query("""
        SELECT COUNT(DISTINCT t.userId)
        FROM Transaction t
        WHERE t.status = 'SUCCESS'
    """)
    Long getCustomersWithPurchase();
}