package com.example.linkShortener.controller;

import com.example.linkShortener.service.LinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RedirectController {

    private final LinkService linkService;

    public RedirectController(LinkService linkService) {

        this.linkService = linkService;
    }

    @GetMapping("{code}")
    public void redirectLink(@PathVariable String code,
                             HttpServletResponse response) throws IOException {

        String originalUrl = linkService.redirectUrl(code);

        response.sendRedirect(originalUrl);
    }
}
