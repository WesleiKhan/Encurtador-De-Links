package com.example.linkShortener.controller;

import com.example.linkShortener.service.DTOs.UrlOriginalEntry;
import com.example.linkShortener.service.DTOs.LinkResponseDTO;
import com.example.linkShortener.service.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {

        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<LinkResponseDTO> shorten(@RequestBody UrlOriginalEntry url) {

        LinkResponseDTO response = linkService.createShortLink(url);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/redirect/{code}")
    public void redirectLink(@PathVariable String code,
                             HttpServletResponse response) throws IOException {

        String originalUrl = linkService.redirectUrl(code);

        response.sendRedirect(originalUrl);
    }
}
