package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.SectionWithVideosDTO;
import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.SectionRepository;
import com.wetech.backend_spring_wetech.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.wetech.backend_spring_wetech.utils.CloudinaryUtils;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    CloudinaryUtils cloudinaryUtils;

    public List<Video> findBySectionId(Long sectionId) {
        return videoRepository.findBySectionId(sectionId);
    }

    public List<SectionWithVideosDTO> findByCourseId(Long courseId) {

        List<SectionWithVideosDTO> result = new ArrayList<>();
        List<Section> sections = sectionRepository.findByCourseId(courseId);
        for (Section section : sections) {
            // Lấy danh sách video thuộc section đó
            List<Video> videos = videoRepository.findBySectionId(section.getSectionId());
            // Gộp lại thành DTO
            SectionWithVideosDTO dto = new SectionWithVideosDTO(
                    section.getSectionId(),
                    section.getName(),
                    videos,
                    section.getCourseId()
            );
            result.add(dto);
        }
        return result;
    }

    public Video create(Long sectionId){
        Video newVideo = new Video();
        newVideo.setSectionId(sectionId);
        return videoRepository.save(newVideo);
    }

    public Video update(Video videoInfo){
        return videoRepository.save(videoInfo);
    }

    public boolean delete(Long videoId) {
        try {
            Video video = videoRepository.findFirstByVideoId(videoId);
            cloudinaryUtils.deleteFromCloudinary(video.getLink());
            videoRepository.deleteById(videoId);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
