package com.wetech.backend_spring_wetech.service;

import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.repository.ProcedureRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProcedureService {

    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private Cloudinary cloudinary;

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

    public Procedure create(Procedure procedure,  MultipartFile image) throws IOException {
        String imageUrl = "";
        if(image != null){
            imageUrl = uploadToCloudinary(image);
        }

        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        procedure.setLinkImage(imageUrl);
        procedure.setCreatedAt(date);
        return procedureRepository.save(procedure);
    }

    public Procedure update(Procedure procedure, MultipartFile image) throws IOException {
        String imageUrl = procedure.getLinkImage();
        if(image != null){
            imageUrl = uploadToCloudinary(image);
        }
        procedure.setLinkImage(imageUrl);
        return procedureRepository.save(procedure);
    }

    public boolean delete(Long procedureId){
        try {
            procedureRepository.deleteById(procedureId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        return uploadResult.get("secure_url").toString(); // link ảnh trực tiếp
    }

}
