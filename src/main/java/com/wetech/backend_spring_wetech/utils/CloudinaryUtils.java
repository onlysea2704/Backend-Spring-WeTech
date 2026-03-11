package com.wetech.backend_spring_wetech.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryUtils {

    @Autowired
    Cloudinary cloudinary;

    // Upload a file to Cloudinary and return the secure URL
    public String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        // Lấy tên file để check đuôi
        String originalFilename = file.getOriginalFilename();
        String lower = "";
        if (originalFilename != null) {
            lower = originalFilename.toLowerCase();
        }
        // Tạo public_id ngẫu nhiên
        String publicId = UUID.randomUUID().toString();
        // Nếu file là .docx hoặc .pdf → thêm extension vào public_id
        if (lower.endsWith(".docx")) {
            publicId += ".docx";
        } else if (lower.endsWith(".pdf")) {
            publicId += ".pdf";
        }

        // Chọn resource_type: với các file như pdf/docx/zip/... nên để raw để truy cập trực tiếp
        String resourceType = "auto";
        if (lower.endsWith(".docx") || lower.endsWith(".pdf") || lower.endsWith(".doc") || lower.endsWith(".zip")) {
            resourceType = "raw";
        }

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", resourceType,
                        "public_id", publicId
                )
        );

        if (uploadResult == null) {
            return null;
        }
        Object secure = uploadResult.get("secure_url");
        return (secure != null) ? secure.toString() : null;
    }

    // Update (overwrite) an existing Cloudinary resource identified by its URL.
    // If public id can't be extracted, falls back to a fresh upload.
    public String updateToCloudinary(MultipartFile file, String existingUrl) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String publicIdOld = extractPublicIdFromUrl(existingUrl);
        if (publicIdOld == null) {
            return uploadToCloudinary(file);
        }

        // Determine new file extension and resource type based on the new file
        String originalFilename = file.getOriginalFilename();
        String lowerNew = originalFilename == null ? "" : originalFilename.toLowerCase();
        String extNew = null;
        if (lowerNew.endsWith(".pdf")) extNew = ".pdf";
        else if (lowerNew.endsWith(".docx")) extNew = ".docx";

        String resourceTypeNew = "auto";
        if (extNew != null) resourceTypeNew = "raw";

        // Check if old public id had an extension (for raw files we kept extension in public id)
        String publicIdBase = publicIdOld;
        String extOld = null;
        if (publicIdOld.toLowerCase().endsWith(".pdf")) {
            extOld = ".pdf";
            publicIdBase = publicIdOld.substring(0, publicIdOld.length() - 4);
        } else if (publicIdOld.toLowerCase().endsWith(".docx")) {
            extOld = ".docx";
            publicIdBase = publicIdOld.substring(0, publicIdOld.length() - 5);
        }

        // If both old and new have raw extensions and they differ, upload under a new public id with extNew,
        // then delete old resource. Otherwise, overwrite the same public id.
        String targetPublicId;
        boolean willDeleteOld = false;

        if (extNew != null && extOld != null && !extNew.equals(extOld)) {
            targetPublicId = publicIdBase + extNew; // new public id with correct extension
            willDeleteOld = true;
        } else if (extNew != null && extOld == null) {
            // old had no extension but new is raw -> append extNew to public id
            targetPublicId = publicIdOld + extNew;
            willDeleteOld = true;
        } else {
            // either both null (non-raw), or same extension -> overwrite in place
            targetPublicId = publicIdOld;
        }

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", resourceTypeNew,
                        "public_id", targetPublicId,
                        "overwrite", true,
                        "invalidate", true
                )
        );

        String newUrl = null;
        if (uploadResult != null) {
            Object secure = uploadResult.get("secure_url");
            newUrl = (secure != null) ? secure.toString() : null;
        }

        // If we created a new public id (with different ext), delete the old asset to avoid duplicates
        if (newUrl != null && willDeleteOld) {
            try {
                deleteFromCloudinary(existingUrl);
            } catch (Exception ignored) {
                // don't fail the update if delete fails; but in production you should log
            }
        }

        return newUrl;
    }

    public String extractPublicIdFromUrl(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        try {
            // Lấy phần sau "/upload/"
            String[] parts = url.split("/upload/");
            if (parts.length < 2) return null;
            String afterUpload = parts[1]; // v1732523450/folder20250101_120000_video.mp4
            // Bỏ "v1234567890/"
            String[] subParts = afterUpload.split("/", 2);
            if (subParts.length < 2) return null;
            String filename = subParts[1]; // folder20250101_120000_video.mp4

            if (filename.toLowerCase().endsWith(".docx") || filename.toLowerCase().endsWith(".pdf")) {
                return filename; // keep extension for raw files (docx/pdf)
            }
            int idx = filename.lastIndexOf('.') ;
            if (idx <= 0) return filename;
            return filename.substring(0, idx);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteFromCloudinary(String url) {
        try {
            String publicId = extractPublicIdFromUrl(url);
            if (publicId == null) {
                System.out.println("Không tìm thấy public_id trong URL");
                return;
            }
            String resourceType = detectResourceType(url);
            Map<?, ?> result = cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap("resource_type", resourceType, "invalidate", true)
            );
            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String detectResourceType(String url) {
        if (url == null) return "raw";
        String lower = url.toLowerCase();
        // Nếu URL chứa đuôi file thường là raw (pdf/docx/...)
        if (lower.endsWith(".pdf") || lower.endsWith(".docx") || lower.endsWith(".doc") || lower.endsWith(".zip")) {
            return "raw";
        }
        if (lower.contains("/image/")) {
            return "image";
        }
        if (lower.contains("/video/")) {
            return "video";
        }
        // mặc định raw
        return "raw";
    }
}
