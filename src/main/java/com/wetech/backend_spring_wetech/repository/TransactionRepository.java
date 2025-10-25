package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.dto.TransactionUserDTO;
import com.wetech.backend_spring_wetech.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Transaction findByCode(String code);

    @Query("""
                SELECT COALESCE(SUM(t.transferAmount), 0)
                FROM Transaction t
                JOIN ListItem l ON t.idTransaction = l.idTransaction
                WHERE l.typeItem = :type
                  AND t.status = 'SUCCESS'
            """)
    Double getRevenueByType(@Param("type") String type);

    @Query("""
                SELECT COALESCE(SUM(t.transferAmount), 0)
                FROM Transaction t
                JOIN ListItem l ON t.idTransaction = l.idTransaction
                WHERE l.typeItem = :type
                  AND MONTH(t.transactionDate) = :month
                  AND YEAR(t.transactionDate) = :year
                  AND t.status = 'SUCCESS'
            """)
    Double getRevenueByTypeAndMonth(@Param("type") String type,
                                    @Param("month") int month,
                                    @Param("year") int year);

    @Query("""
                SELECT COALESCE(SUM(t.transferAmount), 0)
                FROM Transaction t
                WHERE MONTH(t.transactionDate) = :month
                  AND YEAR(t.transactionDate) = :year
                  AND t.status = 'SUCCESS'
            """)
    Double getTotalRevenueByMonth(@Param("month") int month, @Param("year") int year);

    @Query("""
        SELECT new com.wetech.backend_spring_wetech.dto.TransactionUserDTO(
            t.idTransaction,
            t.transferAmount,
            t.transactionDate,
            t.status,
            t.code,
            u.fullName,
            u.sdt
        )
        FROM Transaction t
        JOIN User u ON t.userId = u.userId
        ORDER BY t.transactionDate DESC
    """)
    List<TransactionUserDTO> getAllTransactionsWithUserInfo();
}

