package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.dto.SectionWithDocumentDTO;
import com.wetech.backend_spring_wetech.entity.DocumentSection;
import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.DocumentSectionRepository;
import com.wetech.backend_spring_wetech.repository.SectionRepository;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DocumentService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private DocumentSectionRepository documentSectionRepository;
    @Autowired
    CloudinaryUtils cloudinaryUtils;

    public List<DocumentSection> getDocumentBySectionId(Long sectionId){
        List<DocumentSection> documentSections = documentSectionRepository.findBySectionId(sectionId);
        return documentSections;
    }

    public List<SectionWithDocumentDTO> findByCourseId(Long courseId) {

        List<Section> sections = sectionRepository.findByCourseId(courseId);
        List<SectionWithDocumentDTO> result = new ArrayList<>();
        // Lặp qua từng section
        for (Section section : sections) {
            // Lấy danh sách document thuộc section đó
            List<DocumentSection> documentSections = documentSectionRepository.findBySectionId(section.getSectionId());
            // Gộp lại thành DTO
            SectionWithDocumentDTO dto = new SectionWithDocumentDTO(
                    section.getSectionId(),
                    section.getName(),
                    documentSections,
                    section.getCourseId()
            );
            result.add(dto);
        }
        return result;
    }

    public DocumentSection create(Long sectionId){
        DocumentSection newDocumentSection = new DocumentSection();
        newDocumentSection.setSectionId(sectionId);
        return documentSectionRepository.save(newDocumentSection);
    }

    public DocumentSection update(DocumentSection documentSection, MultipartFile document) throws IOException {
        String documentUrl = documentSection.getLink();
        if(document != null) {
            if(documentUrl != null && !documentUrl.isEmpty()) {
                cloudinaryUtils.deleteFromCloudinary(documentUrl);
            }
            documentUrl = cloudinaryUtils.uploadToCloudinary(document);
        }
        documentSection.setLink(documentUrl);
        return documentSectionRepository.save(documentSection);
    }

    public boolean deleteDocument(Long documentId){
        try {
            DocumentSection documentSection = documentSectionRepository.findFirstByDocumentId(documentId);
            cloudinaryUtils.deleteFromCloudinary(documentSection.getLink());
            documentSectionRepository.deleteById(documentId);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
