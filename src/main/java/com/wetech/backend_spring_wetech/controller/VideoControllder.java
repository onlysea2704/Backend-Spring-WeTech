package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoControllder {

    @Autowired
    private VideoRepository videoRepository;


}
