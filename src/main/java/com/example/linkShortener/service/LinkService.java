package com.example.linkShortener.service;

import com.example.linkShortener.model.Link;
import com.example.linkShortener.repositorie.LinkRepository;
import com.example.linkShortener.service.DTOs.FormatUrlWithCodeResponse;
import com.example.linkShortener.service.DTOs.LinkResponseDTO;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    private final UrlShortenerService urlShortenerService;

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

    public String redirectUrl(String url) {

        String code = urlCodeExtration(url);

        Link originalLink = linkRepository.findByShortCode(code).orElseThrow();

        originalLink.setClicks(1);

        linkRepository.save(originalLink);

        return originalLink.getOriginalUrl();
    }

    private FormatUrlWithCodeResponse formatUrlWithCode() {
        String base = "https://short.local/";
        String code = urlShortenerService.generatedUniqueCode();

        return new FormatUrlWithCodeResponse(base + code, code);
    }

    private String urlCodeExtration(String link) {
        String regex = "https://short\\.local/([a-zA-Z0-9]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(link);

        if(!matcher.find()) {

            throw new RuntimeException("Url invalida.");
        }

       return matcher.group(1);
    }
}
