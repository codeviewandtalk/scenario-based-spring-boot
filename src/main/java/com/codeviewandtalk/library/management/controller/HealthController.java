package com.codeviewandtalk.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthController {

    @GetMapping("/health")
    public String getHealthStatus() {
        return "Library Management System is up and running!";
    }
}
