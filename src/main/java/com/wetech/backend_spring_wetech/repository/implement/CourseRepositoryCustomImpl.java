package com.wetech.backend_spring_wetech.repository.implement;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.repository.CourseRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CourseRepositoryCustomImpl implements CourseRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Course> getTop() {
        String sql = "select c from Course c order by c.numberRegister desc limit :li";
        return em.createQuery(sql, Course.class)
                .setParameter("li", 20)
                .getResultList();
    }

    @Override
    public List<Course> findByType(List<String> types) {
        String sql = "select c from Course c where c.typeCourse in :types";
        return em.createQuery(sql, Course.class)
                .setParameter("types", types)
                .getResultList();
    }

}
