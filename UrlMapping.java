package com.example.urlshortener;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortUrlKey;

    private LocalDateTime createdAt;

    public UrlMapping() {}

    public UrlMapping(String originalUrl, String shortUrlKey) {
        this.originalUrl = originalUrl;
        this.shortUrlKey = shortUrlKey;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters...
    public Long getId() { return id; }
    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
    public String getShortUrlKey() { return shortUrlKey; }
    public void setShortUrlKey(String shortUrlKey) { this.shortUrlKey = shortUrlKey; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
