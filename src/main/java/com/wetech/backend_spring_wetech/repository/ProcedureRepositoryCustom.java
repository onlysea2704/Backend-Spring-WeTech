package com.wetech.backend_spring_wetech.repository;

import com.wetech.backend_spring_wetech.entity.Procedure;

import java.util.List;

public interface ProcedureRepositoryCustom {

    public List<Procedure> getTop();
    public List<Procedure> findByType(String type);
}
