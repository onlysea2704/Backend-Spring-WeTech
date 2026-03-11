package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.dto.FormDTO;
import com.wetech.backend_spring_wetech.dto.procedure.MyProcedureResultDTO;
import com.wetech.backend_spring_wetech.dto.procedure.ProcedureDTO;
import com.wetech.backend_spring_wetech.dto.procedure.ProcedureGroupDTO;
import com.wetech.backend_spring_wetech.entity.Form;
import com.wetech.backend_spring_wetech.entity.MyProcedure;
import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.repository.MyProcedureRepository;
import com.wetech.backend_spring_wetech.repository.ProcedureRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Slf4j
public class ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final Cloudinary cloudinary;
    private final FormService formService;
    private final MyProcedureRepository myProcedureRepository;
    private final UserService userService; // injected to resolve current user
    private final CodeGenerationService codeGenerationService;

    public List<ProcedureDTO> getAll() {
        List<Procedure> procedures = procedureRepository.findAll();
        return procedures.stream().map(ProcedureDTO::new).toList();
    }

    public List<ProcedureDTO> getTop(){
        List<Procedure> procedures = procedureRepository.getTop();
        return procedures.stream().map(ProcedureDTO::new).toList();
    }

    public List<ProcedureDTO> findByType(String type){
        List<Procedure> procedures = procedureRepository.findByServiceType(type);
        return procedures.stream().map(ProcedureDTO::new).toList();
    }

    public List<ProcedureGroupDTO> findByTypeCompany(String typeCompany){
        List<Procedure> procedures = procedureRepository.findByTypeCompany(typeCompany);

        // group procedures by serviceType
        Map<String, List<Procedure>> grouped = procedures.stream()
                .collect(Collectors.groupingBy(Procedure::getServiceType));

        // convert to ProcedureGroupDTO
        List<ProcedureGroupDTO> result = new ArrayList<>();
        for (Map.Entry<String, List<Procedure>> entry : grouped.entrySet()){
            String serviceType = entry.getKey();
            // get original Procedure list for this group so we can extract serviceTypeTitle
            List<Procedure> procs = entry.getValue();
            List<ProcedureDTO> dtos = procs.stream().map(ProcedureDTO::new).toList();
            String serviceTypeTitle = procs.stream()
                    .findFirst()
                    .map(Procedure::getServiceTypeTitle)
                    .orElse(null);
            result.add(new ProcedureGroupDTO(serviceType, serviceTypeTitle, dtos));
        }

        return result;
    }

    public Procedure findById(Long id){
        return procedureRepository.findById(id).orElseThrow(() -> new RuntimeException("Procedure not found"));
    }

    public List<ProcedureDTO> findMyProcedure(Long userId){
        List<Procedure> procedures = procedureRepository.findMyProcedureByUserId(userId);
        return procedures.stream().map(ProcedureDTO::new).toList();
    }

    public ProcedureDTO create(ProcedureDTO procedureDTO, MultipartFile image) throws IOException {
        Procedure procedure = new Procedure();
        convertToProcedure(procedureDTO, procedure, image);

        // ensure unique code for procedure
        if (procedure.getCode() == null || procedure.getCode().isEmpty()) {
            String procCode = codeGenerationService.generateProcedureCode();
            procedure.setCode(procCode);
        }

        List<FormDTO> formDTOS = procedureDTO.getForms();
        List<Form> forms = new ArrayList<>();
        if (formDTOS != null) {
            for (FormDTO formDTO : formDTOS) {
                Form form = new Form();
                formService.convertToForm(formDTO, form);
                // ensure unique code for form
                if (form.getCode() == null || form.getCode().isEmpty()) {
                    form.setCode(codeGenerationService.generateFormCode());
                }
                form.setProcedure(procedure);
                forms.add(form);
            }
        }
        procedure.setForms(forms);
        procedure = procedureRepository.save(procedure);

        return new ProcedureDTO(procedure);
    }

    public ProcedureDTO update(Long procedureId, ProcedureDTO newProcedure, MultipartFile image) throws IOException {
        Procedure procedure = findById(procedureId);
        convertToProcedure(newProcedure, procedure, image);
        // preserve or generate code if missing
        if (procedure.getCode() == null || procedure.getCode().isEmpty()) {
            procedure.setCode(codeGenerationService.generateProcedureCode());
        }

        procedure.getForms().clear();
        List<FormDTO> formDTOS = newProcedure.getForms();
        if (formDTOS != null) {
            for (FormDTO formDTO : formDTOS) {
                Form form = new Form();
                formService.convertToForm(formDTO, form);
                // ensure code for form
                if (form.getCode() == null || form.getCode().isEmpty()) {
                    form.setCode(codeGenerationService.generateFormCode());
                }
                form.setProcedure(procedure);
                procedure.getForms().add(form);
            }
        }
        procedure = procedureRepository.save(procedure);
        return new ProcedureDTO(procedure);
    }

    public boolean delete(Long procedureId){
        try {
            procedureRepository.deleteById(procedureId);
            return true;
        }
        catch (Exception e) {
            log.error("Error deleting procedure", e);
            return false;
        }
    }

    public void addForm(Long procedureId, FormDTO formDTO) {
        Procedure procedure = findById(procedureId);
        Form form = new Form();
        formService.convertToForm(formDTO, form);
        // ensure code for form
        if (form.getCode() == null || form.getCode().isEmpty()) {
            form.setCode(codeGenerationService.generateFormCode());
        }
        form.setProcedure(procedure);
        procedure.getForms().add(form);

        procedureRepository.save(procedure);
    }

    public List<MyProcedureResultDTO> searchRegisteredMyProcedures(Long userId, String typeCompany, String serviceType, LocalDateTime startDate, LocalDateTime endDate, String code){
        return myProcedureRepository.searchRegistered(userId, typeCompany, serviceType, startDate, endDate, code);
    }

    public List<MyProcedureResultDTO> searchDraftMyProcedures(Long userId, String typeCompany, String serviceType, LocalDateTime startDate, LocalDateTime endDate){
        return myProcedureRepository.searchDrafts(userId, typeCompany, serviceType, startDate, endDate);
    }

    public boolean updateMyProcedureStatus(Long userId, Long procedureId, MyProcedure.Status status) {
        if (status == null) return false;
        // only allow certain statuses
        if (status != MyProcedure.Status.PENDING && status != MyProcedure.Status.SUCCESS && status != MyProcedure.Status.FAILED) {
            return false;
        }
        int updated = myProcedureRepository.updateStatusByUserIdAndProcedureId(userId, procedureId, status);
        return updated > 0;
    }

    public boolean updateMyProcedureStatusForCurrentUser(Long procedureId, String statusStr) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) throw new IllegalArgumentException("Unauthenticated");
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);

        MyProcedure.Status status;
        try {
            status = MyProcedure.Status.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status. Allowed: PENDING, SUCCESS, FAILED");
        }

        // only accept PENDING, SUCCESS, FAILED
        if (status != MyProcedure.Status.PENDING && status != MyProcedure.Status.SUCCESS && status != MyProcedure.Status.FAILED) {
            throw new IllegalArgumentException("Invalid status. Allowed: PENDING, SUCCESS, FAILED");
        }

        return updateMyProcedureStatus(user.getUserId(), procedureId, status);
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        return uploadResult.get("secure_url").toString(); // link ảnh trực tiếp
    }

    private void convertToProcedure(ProcedureDTO procedureDTO, Procedure procedure, MultipartFile image) {
        procedure.setTitle(procedureDTO.getTitle());
        procedure.setDescription(procedureDTO.getDescription());
        procedure.setServiceType(procedureDTO.getServiceType());
        procedure.setServiceTypeTitle(procedureDTO.getServiceTypeTitle());
        procedure.setRealPrice(procedureDTO.getRealPrice());
        procedure.setSalePrice(procedureDTO.getSalePrice());
        procedure.setTypeCompany(procedureDTO.getTypeCompany());
        procedure.setTypeCompanyTitle(procedureDTO.getTypeCompanyTitle());

        try {
            if (image != null && !image.isEmpty()) {
                String imageUrl = uploadToCloudinary(image);
                procedure.setLinkImage(imageUrl);
            }
        } catch (Exception e) {
            log.error("Error uploading image", e);
        }
    }
}
