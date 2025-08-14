package com.codeviewandtalk.library.management.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HealthController {

    private final RestTemplate restTemplate = new RestTemplate();
    String HEALTH_API_URL = "http://localhost:8080/actuator/health";

    @Value("${greet.message}")
    String message;


    @GetMapping("/health")
    public String getHealthStatus() {
        return message;
    }

}
