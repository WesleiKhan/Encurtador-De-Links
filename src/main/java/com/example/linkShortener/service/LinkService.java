package com.example.linkShortener.service;

import com.example.linkShortener.model.Link;
import com.example.linkShortener.repositorie.LinkRepository;
import com.example.linkShortener.service.DTOs.FormatUrlWithCodeResponse;
import com.example.linkShortener.service.DTOs.LinkResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    private final UrlShortenerService urlShortenerService;

    private static final String base = "https://link-encurtado.com/";

    public LinkService(LinkRepository linkRepository,
                       UrlShortenerService urlShortenerService) {

        this.linkRepository = linkRepository;
        this.urlShortenerService = urlShortenerService;
    }

    public LinkResponseDTO createShortLink(String link) {

        FormatUrlWithCodeResponse response = formatUrlWithCode();

        Link newLink = new Link(response.code(), link);

        linkRepository.save(newLink);

        return new LinkResponseDTO(response.shortUrl(), link);
    }

    private FormatUrlWithCodeResponse formatUrlWithCode() {
        String code = urlShortenerService.generatedUniqueCode();

        return new FormatUrlWithCodeResponse(base + code, code);
    }
}
