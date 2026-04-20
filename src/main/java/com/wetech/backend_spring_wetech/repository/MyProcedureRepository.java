package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.dto.procedure.MyProcedureResultDTO;
import com.wetech.backend_spring_wetech.entity.MyProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MyProcedureRepository extends JpaRepository<MyProcedure, Long> {
        MyProcedure findByUserIdAndProcedureId(Long userId, Long procedureId);

        boolean existsByUserIdAndProcedureId(Long userId, Long procedureId);

        // Registered procedures have status in (PENDING, SUCCESS, FAILED)
        @Query("SELECT new com.wetech.backend_spring_wetech.dto.procedure.MyProcedureResultDTO(p.procedureId, p.serviceType, p.serviceTypeTitle, p.typeCompany, p.typeCompanyTitle, p.code, mp.submissionCount, mp.submissionDate, mp.createdAt, mp.taxAuthority, mp.status) "
                        +
                        "FROM Procedure p JOIN MyProcedure mp ON mp.procedureId = p.procedureId " +
                        "WHERE mp.userId = :userId " +
                        "AND mp.status IN ('PENDING','SUCCESS','FAILED') " +
                        "AND (:typeCompany IS NULL OR p.typeCompany = :typeCompany) " +
                        "AND (:serviceType IS NULL OR p.serviceType = :serviceType) " +
                        "AND (:startDate IS NULL OR mp.submissionDate >= :startDate) " +
                        "AND (:endDate IS NULL OR mp.submissionDate <= :endDate) " +
                        "AND (:code IS NULL OR p.code = :code)" +
                        "ORDER BY mp.submissionDate DESC")
        List<MyProcedureResultDTO> searchRegistered(
                        @Param("userId") Long userId,
                        @Param("typeCompany") String typeCompany,
                        @Param("serviceType") String serviceType,
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate,
                        @Param("code") String code);

        // Draft (temporarily saved) procedures: status NOT IN (PENDING,SUCCESS,FAILED)
        @Query("SELECT new com.wetech.backend_spring_wetech.dto.procedure.MyProcedureResultDTO(p.procedureId, p.serviceType, p.serviceTypeTitle, p.typeCompany, p.typeCompanyTitle, p.code, mp.submissionCount, mp.submissionDate, mp.createdAt, mp.taxAuthority, mp.status) "
                        +
                        "FROM Procedure p JOIN MyProcedure mp ON mp.procedureId = p.procedureId " +
                        "WHERE mp.userId = :userId " +
                        "AND mp.status IN ('DRAFT', 'PAID') " +
                        "AND (:typeCompany IS NULL OR p.typeCompany = :typeCompany) " +
                        "AND (:serviceType IS NULL OR p.serviceType = :serviceType) " +
                        "AND (:startDate IS NULL OR mp.createdAt >= :startDate) " +
                        "AND (:endDate IS NULL OR mp.createdAt <= :endDate)" +
                        "ORDER BY mp.createdAt DESC")
        List<MyProcedureResultDTO> searchDrafts(
                        @Param("userId") Long userId,
                        @Param("typeCompany") String typeCompany,
                        @Param("serviceType") String serviceType,
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

        @Modifying
        @Transactional
        @Query("UPDATE MyProcedure mp SET mp.status = :status, mp.taxAuthority = :taxAuthority, mp.submissionCount = mp.submissionCount + 1, mp.submissionDate = CURRENT_TIMESTAMP WHERE mp.userId = :userId AND mp.procedureId = :procedureId")
        int updateStatusAndTaxAuthorityByUserIdAndProcedureId(@Param("userId") Long userId,
                        @Param("procedureId") Long procedureId, @Param("status") MyProcedure.Status status,
                        @Param("taxAuthority") String taxAuthority);
}
