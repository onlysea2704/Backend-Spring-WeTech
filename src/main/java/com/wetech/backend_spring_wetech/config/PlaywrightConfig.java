package com.wetech.backend_spring_wetech.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Playwright browser instance.
 * Initializes a singleton browser instance to be reused across requests.
 */
@Configuration
@Slf4j
public class PlaywrightConfig {

    /**
     * Creates and manages Playwright instance as a Spring Bean.
     * Launched browsers are started here.
     */
    @Bean
    public Playwright playwright() {
        log.info("Initializing Playwright instance");
        return Playwright.create();
    }

    /**
     * Creates and manages Browser instance as a Spring Bean.
     * Uses Chromium browser for PDF generation.
     * This is a singleton that will be reused across all requests.
     */
    @Bean
    public Browser browser(Playwright playwright) {
        log.info("Launching Chromium browser");
        return playwright.chromium().launch();
    }

}

