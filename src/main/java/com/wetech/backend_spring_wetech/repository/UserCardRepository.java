package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long> {
    Optional<UserCard> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}

