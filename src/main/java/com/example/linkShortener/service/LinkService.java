package com.example.linkShortener.service;

import com.example.linkShortener.exceptions.InvalidEntryException;
import com.example.linkShortener.exceptions.LinkNotFound;
import com.example.linkShortener.model.Link;
import com.example.linkShortener.repositorie.LinkRepository;
import com.example.linkShortener.service.DTOs.FormatUrlWithCodeResponse;
import com.example.linkShortener.service.DTOs.LinkResponseDTO;
import com.example.linkShortener.service.DTOs.UrlOriginalEntry;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    private final UrlShortenerService urlShortenerService;

    public LinkService(LinkRepository linkRepository,
                       UrlShortenerService urlShortenerService) {

        this.linkRepository = linkRepository;
        this.urlShortenerService = urlShortenerService;
    }

    public LinkResponseDTO createShortLink(UrlOriginalEntry link) {

        if(link.url().trim().isEmpty()) {
            throw new InvalidEntryException();
        }

        FormatUrlWithCodeResponse response = formatUrlWithCode();

        Link newLink = new Link(response.code(), link.url());

        linkRepository.save(newLink);

        return new LinkResponseDTO(response.shortUrl(), link.url());
    }

    public String redirectUrl(String code) {

        Link originalLink = linkRepository.findByShortCode(code)
                .orElseThrow(LinkNotFound::new);

        originalLink.incrementClicks();

        linkRepository.save(originalLink);

        return originalLink.getOriginalUrl().replace("\"", "").trim();
    }

    private FormatUrlWithCodeResponse formatUrlWithCode() {
        String base = "http://short.local/";
        String code = urlShortenerService.generatedUniqueCode();

        return new FormatUrlWithCodeResponse(base + code, code);
    }

}
