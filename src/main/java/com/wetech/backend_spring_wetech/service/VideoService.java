package com.wetech.backend_spring_wetech.service;

import com.cloudinary.utils.ObjectUtils;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private Cloudinary cloudinary;

    public List<Video> findBySectionId(Long sectionId) {
        return videoRepository.findBySectionId(sectionId);
    }

    public Video create(Video videoInfo, MultipartFile video) throws IOException {
        String videoUrl = uploadToCloudinary(video);
        videoInfo.setLink(videoUrl);
        return videoRepository.save(videoInfo);
    }

    public Video update(Video videoInfo, MultipartFile video) throws IOException {
        String videoUrl = videoInfo.getLink();
        if(videoInfo.getLink() != null && !videoInfo.getLink().equals("")){
            videoUrl = uploadToCloudinary(video);
        }
        return videoRepository.save(videoInfo);
    }

    public boolean delete(Video videoId) {
        try {
            videoRepository.deleteById(videoId.getVideoId());
            return true;
        }catch (Exception e) {
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
