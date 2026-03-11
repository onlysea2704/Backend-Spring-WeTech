package com.wetech.backend_spring_wetech.repository.implement;

import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.repository.ProcedureRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ProcedureRepositoryCustomImpl implements ProcedureRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public List<Procedure> getTop(){
        String sql = "SELECT p FROM Procedure p ORDER BY p.numberRegister DESC limit :li";
        return em.createQuery( sql,Procedure.class)
                .setParameter("li",20)
                .getResultList();
    }

    public List<Procedure> findByServiceType(String type){
        String sql = "SELECT p FROM Procedure p where p.serviceType = :type";
        return em.createQuery( sql,Procedure.class)
                .setParameter("type",type)
                .getResultList();
    }

    public List<Procedure> findByTypeCompany(String typeCompany){
        String sql = "SELECT p FROM Procedure p where p.typeCompany = :typeCompany";
        return em.createQuery( sql,Procedure.class)
                .setParameter("typeCompany",typeCompany)
                .getResultList();
    }
}
