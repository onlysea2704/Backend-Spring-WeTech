package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long>, ProcedureRepositoryCustom {
    Procedure findFirstByProcedureId(Long id);

    @Query("select P from Procedure P inner join MyProcedure Mp on Mp.procedureId = P.procedureId where Mp.userId = :userId")
    List<Procedure> findMyProcedureByUserId(@Param("userId") Long userId);
}

