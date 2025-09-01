package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.InfoDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoDocumentRepository extends JpaRepository<InfoDocument, Long> {
}

