package com.wetech.backend_spring_wetech.controller;

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
@RequestMapping("/video")
public class VideoControllder {

    @Autowired
    private VideoService videoService;

    @GetMapping("/find-by-sectionId")
    public ResponseEntity<Object> getVideoBySectionId(@RequestParam("sectionId") long sectionId) {
        List<Video> videos = videoService.findBySectionId(sectionId);
        return ResponseEntity.ok(videos);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(
            @RequestPart(value = "sectionId", required = true) Long sectionId,
            @RequestPart(value = "video", required = true) MultipartFile video
    ) throws IOException {
        Video newVideo = videoService.create(sectionId, video);
        return ResponseEntity.ok(newVideo);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long videoId) throws IOException {
        boolean statusDeleted = videoService.delete(videoId);
        return ResponseEntity.ok(statusDeleted);
    }
}
