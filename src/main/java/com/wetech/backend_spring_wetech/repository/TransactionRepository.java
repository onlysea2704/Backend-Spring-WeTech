package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Transaction findByCode(String code);
}

