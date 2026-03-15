package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.FormDTO;
import com.wetech.backend_spring_wetech.dto.procedure.MyProcedureResultDTO;
import com.wetech.backend_spring_wetech.dto.procedure.ProcedureDTO;
import com.wetech.backend_spring_wetech.dto.procedure.ProcedureGroupDTO;
import com.wetech.backend_spring_wetech.entity.MyProcedure;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.service.ProcedureService;
import com.wetech.backend_spring_wetech.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/procedurer")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;
    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public List<ProcedureDTO> getAll() {
        return procedureService.getAll();
    }

    @GetMapping("/get-top")
    public List<ProcedureDTO> getTop() {
        return procedureService.getTop();
    }

    @GetMapping("/find-by-type")
    public List<ProcedureDTO> findByType(@RequestParam("type") String type) {
        return procedureService.findByType(type);
    }

    @GetMapping("/find-by-type-company")
    public List<ProcedureGroupDTO> findByTypeCompany(@RequestParam("typeCompany") String typeCompany) {
        return procedureService.findByTypeCompany(typeCompany);
    }

    @GetMapping("/find-by-id-and-check-status")
    public ProcedureDTO findById(@RequestParam("id") Long id) {
        return procedureService.findByIdAndCheckStatus(id);
    }

    @GetMapping("/find-my-procedure")
    public List<ProcedureDTO> findMyProcedure() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        return procedureService.findMyProcedure(user.getUserId());
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProcedureDTO> create(
            @RequestPart(value = "procedure") ProcedureDTO procedureDTO,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        ProcedureDTO newProcedure = procedureService.create(procedureDTO, image);
        return ResponseEntity.ok(newProcedure);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProcedureDTO> update(
            @RequestPart(value = "procedure") ProcedureDTO procedureDTO,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "procedureId") Long procedureId
    ) throws IOException {
        ProcedureDTO updatedProcedure = procedureService.update(procedureId, procedureDTO, image);
        if (updatedProcedure == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProcedure);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam("procedureId") Long procedureId) {
        boolean updatedStatus = procedureService.delete(procedureId);
        return ResponseEntity.ok(updatedStatus);
    }

    @PostMapping("/add-form")
    public ResponseEntity<String> addForm(@RequestParam("procedureId") Long procedureId, @RequestBody FormDTO formDTO) {
        procedureService.addForm(procedureId, formDTO);
        return ResponseEntity.ok("Form added successfully");
    }

    @GetMapping("/search-registered")
    public List<MyProcedureResultDTO> searchRegistered(
            @RequestParam(value = "typeCompany", required = false) String typeCompany,
            @RequestParam(value = "serviceType", required = false) String serviceType,
            @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate,
            @RequestParam(value = "code", required = false) String code
    ) {
        return procedureService.searchRegisteredMyProcedures(typeCompany, serviceType, startDate, endDate, code);
    }

    @GetMapping("/search-drafts")
    public List<MyProcedureResultDTO> searchDrafts(
            @RequestParam(value = "typeCompany", required = false) String typeCompany,
            @RequestParam(value = "serviceType", required = false) String serviceType,
            @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate
    ) {

        return procedureService.searchDraftMyProcedures(typeCompany, serviceType, startDate, endDate);
    }

    @PostMapping("/update-my-procedure")
    public ResponseEntity<Object> updateMyProcedureStatus(
            @RequestParam("procedureId") Long procedureId,
            @RequestParam("status") MyProcedure.Status status,
            @RequestParam("taxAuthority") String taxAuthority
    ) {
        try {
            boolean updated = procedureService.updateMyProcedureStatusForCurrentUser(procedureId, status, taxAuthority);
            if (!updated) {
                return ResponseEntity.status(404).body("No matching my procedure found");
            }
            return ResponseEntity.ok(true);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download-files")
    public void downloadFiles(
            HttpServletResponse response,
            @RequestParam("procedureId") Long procedureId
    ) {
        procedureService.downloadFiles(response, procedureId);
    }

}
