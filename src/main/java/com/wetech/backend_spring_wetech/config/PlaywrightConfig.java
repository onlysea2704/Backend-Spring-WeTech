package com.wetech.backend_spring_wetech.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class PlaywrightConfig {
    @Bean
    public Playwright playwright() {
        log.info("Initializing Playwright instance");
        return Playwright.create();
    }

    @Bean
    public Browser browser(Playwright playwright) {
        log.info("Launching Chromium browser");
        return playwright.chromium().launch();
    }

}

