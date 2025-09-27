package com.wetech.backend_spring_wetech.service;

import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private Cloudinary cloudinary;

    public List<Video> findBySectionId(Long sectionId) {
        return videoRepository.findBySectionId(sectionId);
    }

    public List<Video> findByCourseId(Long sectionId) {
        return videoRepository.findByCourseId(sectionId);
    }

    public Video create(Long sectionId, MultipartFile video) throws IOException {
        Video newVideo = new Video();
        String videoUrl = uploadToCloudinary(video);
        newVideo.setLink(videoUrl);
        newVideo.setSectionId(sectionId);
        return videoRepository.save(newVideo);
    }

//    public Video update(Video videoInfo, MultipartFile video) throws IOException {
//        String videoUrl = videoInfo.getLink();
//        if(videoInfo.getLink() != null && !videoInfo.getLink().equals("")){
//            videoUrl = uploadToCloudinary(video);
//        }
//        return videoRepository.save(videoInfo);
//    }

    public boolean delete(Long videoId) {
        try {
            videoRepository.deleteById(videoId);
            return true;
        }catch (Exception e) {
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
