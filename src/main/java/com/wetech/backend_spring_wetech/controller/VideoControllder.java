package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.VideoRepository;
import com.wetech.backend_spring_wetech.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Video videoInfo, @RequestParam("video") MultipartFile video) throws IOException {
        Video newVideo = videoService.create(videoInfo, video);
        return ResponseEntity.ok(newVideo);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Video videoInfo, @RequestParam("video") MultipartFile video) throws IOException {
        Video updatedVideo = videoService.update(videoInfo, video);
        return ResponseEntity.ok(updatedVideo);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody Video video) throws IOException {
        boolean statusDeleted = videoService.delete(video);
    }
}
