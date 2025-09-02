package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.service.ProcedureService;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedurer")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    @GetMapping("get-all")
    public List<Procedure> getAll(){
        return procedureService.getAll();
    }

    @GetMapping("get-top")
    public List<Procedure> getTop(){
        return procedureService.getTop();
    }

    @GetMapping("/find-by-type")
    public List<Procedure> findByType(@RequestParam String type){
        return procedureService.findByType(type);
    }

    @GetMapping("/find-by-id")
    public Procedure findById(@RequestParam Long id){
        return procedureService.findById(id);
    }

//    @PostMapping("/admin/create")
//    public Procedure create(@RequestBody Procedure procedure){
//
//    }
//
//    @PostMapping("/admin/update")
//    public Procedure update(@RequestBody Procedure procedure){
//
//    }
//
//    @PostMapping("admin/delete")
//    public Procedure create(@RequestBody Procedure procedure){
//
//    }
}
