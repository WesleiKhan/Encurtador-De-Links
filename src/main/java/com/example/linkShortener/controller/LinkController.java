package com.example.linkShortener.controller;

import com.example.linkShortener.service.DTOs.LinkResponseDTO;
import com.example.linkShortener.service.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {

        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<LinkResponseDTO> shorten(@RequestBody String url) {

        LinkResponseDTO response = linkService.createShortLink(url);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/redirect")
    public ResponseEntity<String> redirectLink(@RequestBody String url) {

        String originalUrl = linkService.redirectUrl(url);

        return ResponseEntity.status(302).body(originalUrl);
    }
}
