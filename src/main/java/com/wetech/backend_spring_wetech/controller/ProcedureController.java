package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.service.ProcedureService;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedurer")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;
    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public List<Procedure> getAll(){
        return procedureService.getAll();
    }

    @GetMapping("/get-top")
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

    @GetMapping("/find-my-procedure")
    public List<Procedure> findMyProcedure(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        return procedureService.findMyProcedure(user.getUserId());
    }


//    @GetMapping("/find-my-procedure")
//    public List<Procedure> findMyProcedure(){
//    return procedureService
//    }

    @PostMapping("/create")
    public ResponseEntity<Procedure> create(@RequestBody Procedure procedure){
        Procedure newProcedure =  procedureService.create(procedure);
        return ResponseEntity.ok(newProcedure);
    }

    @PostMapping("/update")
    public ResponseEntity<Procedure> update(@RequestBody Procedure procedure){
        Procedure updatedProcedure =  procedureService.create(procedure);
        return ResponseEntity.ok(updatedProcedure);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody Procedure procedure){
        boolean updatedStatus = procedureService.delete(procedure);
        return ResponseEntity.ok(updatedStatus);
    }
}
