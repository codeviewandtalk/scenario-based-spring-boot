package com.codeviewandtalk.library.management.controller;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final CacheManager cacheManager;

    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCache(@RequestParam(required = false) String cacheName) {
        if (cacheName != null) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
                return ResponseEntity.ok("Cache '" + cacheName + "' cleared successfully");
            }
            return ResponseEntity.notFound().build();
        }

        // Clear all caches if no specific cache name provided
        cacheManager.getCacheNames().forEach(name ->
                cacheManager.getCache(name).clear()
        );
        return ResponseEntity.ok("All caches cleared successfully");
    }
}
