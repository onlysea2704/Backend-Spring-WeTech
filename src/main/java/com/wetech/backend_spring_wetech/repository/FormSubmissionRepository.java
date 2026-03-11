package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Long> {

    // Return the most recent form submission for a given formId and userId (avoid NonUniqueResultException)
    FormSubmission findTopByFormFormIdAndUserUserIdOrderByCreatedAtDesc(Long formId, Long userId);
}
