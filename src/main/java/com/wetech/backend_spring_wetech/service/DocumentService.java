package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.entity.DocumentSection;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.DocumentSectionRepository;
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
    private DocumentSectionRepository documentSectionRepository;

    public List<DocumentSection> getDocumentBySectionId(Long sectionId){
        List<DocumentSection> documentSections = documentSectionRepository.findBySectionId(sectionId);
        return documentSections;
    }

    public DocumentSection createDocument(Long sectionId, MultipartFile document) throws IOException {
        DocumentSection newDocument = new DocumentSection();
        String videoUrl = uploadToCloudinary(document);
        newDocument.setLink(videoUrl);
        newDocument.setSectionId(sectionId);
        return documentSectionRepository.save(newDocument);
    }

    public boolean deleteDocument(Long documentId){
        try {
            documentSectionRepository.deleteById(documentId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        // Lấy tên gốc (vd: report.docx)
        String originalFilename = file.getOriginalFilename();
        // Lấy timestamp theo giờ phút giây
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // Tạo tên file mới (vd: 20250913_235959.docx)
        String newFilename = timestamp + originalFilename;
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",  // Cloudinary sẽ tự động đoán loại file để phân loại vào image, video hoặc raw
                        "public_id", newFilename
                )
        );
        return uploadResult.get("secure_url").toString(); // link ảnh trực tiếp
    }
}
