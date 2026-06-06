package com.sufiyan.url_shortener.service;

import com.sufiyan.url_shortener.model.UrlMapping;
import com.sufiyan.url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public UrlMapping createShortUrl(String originalUrl) {
        String shortCode = UUID.randomUUID().toString().substring(0, 6);
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(shortCode);
        mapping.setCreatedAt(LocalDateTime.now());
        return urlRepository.save(mapping);
    }

    public UrlMapping getByShortCode(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
    }

    public UrlMapping trackClick(String shortCode) {
        UrlMapping mapping = getByShortCode(shortCode);
        mapping.setClickCount(mapping.getClickCount() + 1);
        return urlRepository.save(mapping);
    }

    public List<UrlMapping> getAllUrls() {
        return urlRepository.findAll();
    }
}