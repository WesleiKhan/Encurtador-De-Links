package com.example.linkShortener.controller;

import com.example.linkShortener.model.Link;
import com.example.linkShortener.service.DTOs.UrlOriginalEntry;
import com.example.linkShortener.service.DTOs.LinkResponseDTO;
import com.example.linkShortener.service.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("encurtador-links")
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

    @GetMapping("/links")
    public ResponseEntity<List<Link>> getLinks() {

        List<Link> links = linkService.seeLinks();

        return ResponseEntity.ok().body(links);
    }

}
