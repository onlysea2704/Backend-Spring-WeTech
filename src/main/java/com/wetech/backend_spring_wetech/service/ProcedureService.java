package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.repository.ProcedureRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {

    @Autowired
    private ProcedureRepository procedureRepository;


    public List<Procedure> getAll() {
        return procedureRepository.findAll();
    }

    public List<Procedure> getTop(){
        return procedureRepository.getTop();
    }

    public List<Procedure> findByType(String type){
        return procedureRepository.findByType(type);
    }

    public Procedure findById(Long id){
        return procedureRepository.findFirstByProcedureId(id);
    }

    public List<Procedure> findMyProcedure(Long userId){
        return procedureRepository.findMyProcedureByUserId(userId);
    }
}
