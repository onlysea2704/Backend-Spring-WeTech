package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.service.ProcedureService;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/procedurer")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;
    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public List<Procedure> getAll() {
        return procedureService.getAll();
    }

    @GetMapping("/get-top")
    public List<Procedure> getTop() {
        return procedureService.getTop();
    }

    @GetMapping("/find-by-type")
    public List<Procedure> findByType(@RequestParam String type) {
        return procedureService.findByType(type);
    }

    @GetMapping("/find-by-id")
    public Procedure findById(@RequestParam Long id) {
        return procedureService.findById(id);
    }

    @GetMapping("/find-my-procedure")
    public List<Procedure> findMyProcedure() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        return procedureService.findMyProcedure(user.getUserId());
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Procedure> create(
            @RequestPart(value = "procedure", required = true) Procedure procedure,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        Procedure newProcedure = procedureService.create(procedure, image);
        return ResponseEntity.ok(newProcedure);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Procedure> update(
            @RequestPart(value = "procedure", required = true) Procedure procedure,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Procedure updatedProcedure = procedureService.update(procedure, image);
        return ResponseEntity.ok(updatedProcedure);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long procedureId) {
        boolean updatedStatus = procedureService.delete(procedureId);
        return ResponseEntity.ok(updatedStatus);
    }
}
