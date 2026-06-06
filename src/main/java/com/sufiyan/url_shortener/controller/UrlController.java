package com.sufiyan.url_shortener.controller;;

import com.sufiyan.url_shortener.model.UrlMapping;
import com.sufiyan.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlMapping> createShortUrl(@RequestBody Map<String, String> request) {
        UrlMapping mapping = urlService.createShortUrl(request.get("originalUrl"));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapping);
    }

    @GetMapping
    public ResponseEntity<List<UrlMapping>> getAllUrls() {
        return ResponseEntity.ok(urlService.getAllUrls());
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlMapping> redirect(@PathVariable String shortCode) {
        UrlMapping mapping = urlService.trackClick(shortCode);
        return ResponseEntity.ok(mapping);
    }

    @GetMapping("/{shortCode}/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics(@PathVariable String shortCode) {
        UrlMapping mapping = urlService.getByShortCode(shortCode);
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("originalUrl", mapping.getOriginalUrl());
        analytics.put("shortCode", mapping.getShortCode());
        analytics.put("clickCount", mapping.getClickCount());
        analytics.put("createdAt", mapping.getCreatedAt());
        return ResponseEntity.ok(analytics);
    }
}