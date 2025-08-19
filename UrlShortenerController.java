package com.example.urlshortener;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrls(@RequestBody Map<String, List<String>> request) {
        List<String> urls = request.get("urls");
        if (urls == null || urls.isEmpty()) {
            return ResponseEntity.badRequest().body("URLs must not be empty");
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (String url : urls) {
            try {
                String shortKey = service.shortenUrl(url);
                result.add(Map.of("originalUrl", url, "shortUrlKey", shortKey));
            } catch (Exception e) {
                result.add(Map.of("originalUrl", url, "error", "Could not shorten URL"));
            }
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{shortKey}")
    public void redirectToOriginal(@PathVariable String shortKey, HttpServletResponse response) throws IOException {
        service.getOriginalUrl(shortKey).ifPresentOrElse(
                originalUrl -> {
                    try {
                        response.sendRedirect(originalUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL not found");
                    } catch (IOException ignored) {}
                }
        );
    }
}
