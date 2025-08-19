package com.example.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Optional;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlMappingRepository repository;

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String shortenUrl(String originalUrl) throws Exception {
        String shortKey = generateShortKey(originalUrl);
        // Check collision and regenerate if necessary (simple example, no loop)
        Optional<UrlMapping> existing = repository.findByShortUrlKey(shortKey);
        if (existing.isPresent()) {
            // Append random or timestamp to originalUrl and regenerate
            shortKey = generateShortKey(originalUrl + System.currentTimeMillis());
        }
        UrlMapping mapping = new UrlMapping(originalUrl, shortKey);
        repository.save(mapping);
        return shortKey;
    }

    public Optional<String> getOriginalUrl(String shortKey) {
        return repository.findByShortUrlKey(shortKey).map(UrlMapping::getOriginalUrl);
    }

    private String generateShortKey(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());
        return base62Encode(digest).substring(0, 6); // 6 chars short key
    }

    private String base62Encode(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        long num = 0;
        for (int i = 0; i < 6 && i < bytes.length; i++) {
            num = (num << 8) + (bytes[i] & 0xFF);
        }
        while (sb.length() < 6) {
            sb.append(BASE62.charAt((int)(num % 62)));
            num /= 62;
        }
        return sb.toString();
    }
}
