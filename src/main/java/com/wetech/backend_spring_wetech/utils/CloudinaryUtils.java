package com.wetech.backend_spring_wetech.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryUtils {

    @Autowired
    Cloudinary cloudinary;

//    public String uploadToCloudinary(MultipartFile file) throws IOException {
//        if (file == null || file.isEmpty()) {
//            return null;
//        }
//        // Lấy tên gốc (vd: report.docx)
//        String originalFilename = file.getOriginalFilename();
//        // Lấy timestamp theo giờ phút giây
//        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        // Tạo tên file mới (vd: 20250913_235959.docx)
//        String newFilename = timestamp + originalFilename;
//        Map uploadResult = cloudinary.uploader().upload(
//                file.getBytes(),
//                ObjectUtils.asMap(
//                        "resource_type", "auto",  // Cloudinary sẽ tự động đoán loại file để phân loại vào image, video hoặc raw
//                        "public_id", newFilename
//                )
//        );
//        return uploadResult.get("secure_url").toString(); // link ảnh trực tiếp
//    }

    public String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        // Lấy tên file để check đuôi
        String originalFilename = file.getOriginalFilename();
        String lower = originalFilename.toLowerCase();
        // Tạo public_id ngẫu nhiên
        String publicId = UUID.randomUUID().toString();
        // Nếu file là .docx → thêm .docx vào public_id
        if (lower.endsWith(".docx")) {
            publicId += ".docx";
        }
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "public_id", publicId
                )
        );
        return uploadResult.get("secure_url").toString();
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

            if (filename.toLowerCase().endsWith(".docx")) {
                return filename;
            }
            // Bỏ phần mở rộng .mp4
            return filename.substring(0, filename.lastIndexOf('.'));
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteFromCloudinary(String url) {
        try {
            String publicId = extractPublicIdFromUrl(url);
            if (publicId == null) {
                System.out.println("Không tìm thấy public_id trong URL");
            }
            String resourceType = detectResourceType(url);
            Map result = cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap("resource_type", resourceType, "invalidate", true)
            );
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String detectResourceType(String url) {
        if (url.contains("/image/")) {
            return "image";
        }
        if (url.contains("/video/")) {
            return "video";
        }
        // trường hợp không thấy => file docx, pdf, zip
        // => Cloudinary mặc định raw
        return "raw";
    }
}
