package com.wetech.backend_spring_wetech.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class PlaywrightConfig {
    @Bean
    public Playwright playwright() {
        log.info("Initializing Playwright instance");
        Map<String, String> env = new HashMap<>();
        // Skip the default behavior which downloads all browsers (Chromium, Firefox,
        // WebKit)
        env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
        return Playwright.create(new Playwright.CreateOptions().setEnv(env));
    }

    @Bean
    public Browser browser(Playwright playwright) {
        log.info("Launching Chromium browser");
        return playwright.chromium().launch();
    }

}
