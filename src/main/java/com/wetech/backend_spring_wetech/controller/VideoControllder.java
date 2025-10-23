package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.SectionWithVideosDTO;
import com.wetech.backend_spring_wetech.entity.Procedure;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.VideoRepository;
import com.wetech.backend_spring_wetech.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/video")
public class VideoControllder {

    @Autowired
    private VideoService videoService;

    @GetMapping("/find-by-sectionId")
    public ResponseEntity<Object> getVideoBySectionId(@RequestParam("sectionId") long sectionId) {
        List<Video> videos = videoService.findBySectionId(sectionId);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/find-by-courseId")
    public ResponseEntity<Object> getVideoByCourseId(@RequestParam("courseId") long courseId) {
        List<SectionWithVideosDTO> videos = videoService.findByCourseId(courseId);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> createVideo(@RequestParam Long sectionId){
        Video video = videoService.create(sectionId);
        return ResponseEntity.ok(video);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(
            @RequestPart(value = "videoInfo", required = true) Video videoInfo,
            @RequestPart(value = "video", required = false) MultipartFile video
    ) throws IOException {
        System.out.println("videoInfo: " + videoInfo);
        Video newVideo = videoService.update(videoInfo, video);
        return ResponseEntity.ok(newVideo);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long videoId) throws IOException {
        boolean statusDeleted = videoService.delete(videoId);
        return ResponseEntity.ok(statusDeleted);
    }
}
