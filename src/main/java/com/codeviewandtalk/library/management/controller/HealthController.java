package com.codeviewandtalk.library.management.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Value("${greet.message}")
    String message;

    @GetMapping("/health")
    public String getHealthStatus() {
        return message;
    }
}
