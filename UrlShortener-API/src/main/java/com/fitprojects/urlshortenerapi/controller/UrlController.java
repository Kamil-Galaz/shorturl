package com.fitprojects.urlshortenerapi.controller;

import com.fitprojects.urlshortenerapi.dto.UrlLongRequest;
import com.fitprojects.urlshortenerapi.entity.Url;
import com.fitprojects.urlshortenerapi.service.UrlService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody UrlLongRequest request) {
        return urlService.convertToShortUrl(request);
    }

    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping(value = "{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @ApiOperation(value = "List of URLs", notes = "List of all existing shortened URLs in the system")
    @PostMapping(value = "urlList")
    public List<Url> showUrls(@RequestBody UrlLongRequest request) {
        return urlService.showUrls();
    }

}
