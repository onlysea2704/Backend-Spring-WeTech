package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long> {
    @Query("SELECT uc FROM UserCard uc WHERE uc.userId = :userId")
    List<UserCard> findByUserId(Long userId);

    UserCard findByIdAndUserId(Long id, Long userId);

    boolean existsByUserId(Long userId);
}

