package com.wetech.backend_spring_wetech.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class PlaywrightConfig {
    @Bean
    public Playwright playwright() {
        return Playwright.create();
    }



}
