package com.wetech.backend_spring_wetech.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CloudinaryUtils {

    private final Cloudinary cloudinary;

    // ===============================
    // Upload file
    // ===============================
    public String uploadToCloudinary(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String publicId = UUID.randomUUID().toString();

        assert fileName != null;
        if (fileName.endsWith(".docx")) {
            publicId += ".docx";
        }

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "public_id", publicId
                )
        );
        System.out.println("public id from upload: " + uploadResult.get("public_id"));
        return uploadResult.get("secure_url").toString();
    }

    public void deleteFromCloudinary(String oldUrl) throws IOException {
        String publicId = extractPublicId(oldUrl);
        System.out.println("public id from delete: " + publicId);
        System.out.println("resource type from delete: " + detectResourceTypeFromUrl(oldUrl));
        cloudinary.uploader().destroy(
                publicId,
                ObjectUtils.asMap(
                        "resource_type", detectResourceTypeFromUrl(oldUrl)
                )
        );
    }

    private String detectResourceTypeFromUrl(String url) {

        if (url == null) return "raw";

        String lower = url.toLowerCase();

        if (lower.endsWith(".pdf")
                || lower.endsWith(".doc")
                || lower.endsWith(".docx")
                || lower.endsWith(".xlsx")
                || lower.endsWith(".zip")) {
            return "raw";
        }

        if (lower.endsWith(".mp4")
                || lower.endsWith(".mov")
                || lower.endsWith(".avi")) {
            return "video";
        }

        return "image";
    }

    private String extractPublicId(String url) {

        if (url == null || url.isEmpty()) {
            return null;
        }

        try {

            String fileName = url.substring(url.lastIndexOf("/") + 1);

            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex != -1) {
                fileName = fileName.substring(0, dotIndex);
            }

            return fileName;

        } catch (Exception e) {
            return null;
        }
    }

}
