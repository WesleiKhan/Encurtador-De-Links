package com.example.linkShortener.service;

import com.example.linkShortener.exceptions.InvalidEntryException;
import com.example.linkShortener.exceptions.LinkNotFound;
import com.example.linkShortener.model.Link;
import com.example.linkShortener.repositorie.LinkRepository;
import com.example.linkShortener.service.DTOs.LinkResponseDTO;
import com.example.linkShortener.service.DTOs.UrlOriginalEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkServiceTest {

    @Mock
    LinkRepository linkRepository;

    @Mock
    UrlShortenerService urlShortenerService;

    @InjectMocks
    LinkService linkService;

    @Test
    void createShortLink_withValidInput_returnsLinkResponseDTO() {

        String url = "https://meusite.com/c/67fa8b5b-3434-8006-922a-d0385d0c1815";
        String code = "goTwLqbPauCaPQf61903";
        String shortUrl = "http://short.local/" + code;

        UrlOriginalEntry entry = new UrlOriginalEntry(url);

        when(urlShortenerService.generatedUniqueCode()).thenReturn(code);

        LinkResponseDTO response = linkService.createShortLink(entry);

        assertEquals(shortUrl, response.short_url());
        assertEquals(url, response.original_url());
        verify(linkRepository).save(any(Link.class));
    }

    @Test
    void createShortLink_withEmptyUrl_throwsInvalidEntryExceptio() {

        UrlOriginalEntry entry = new UrlOriginalEntry("  ");

        assertThrows(InvalidEntryException.class,
                () -> linkService.createShortLink(entry));
    }

    @Test
    void redirectUrl_withValidCode_ReturnsOriginalUrl() {
        String code = "goTwLqbPauCaPQf61903";

        String originalUrl = "https://meusite.com/c/67fa8b5b-3434-8006-922a" +
                "-d0385d0c1815";

        String shortUrl = "http://short.local/" + code;

        Link link = new Link(shortUrl, originalUrl);

        when(linkRepository.findByShortCode(code)).thenReturn(java.util.Optional.of(link));

        String result = linkService.redirectUrl(code);

        assertEquals(originalUrl, result);
        verify(linkRepository).save(any(Link.class));
    }

    @Test
    void redirectUrl_withInvalidCode_throwsLinkNotFound() {
        String code = "goTwLqbPauCaPQf61903";

        when(linkRepository.findByShortCode(code)).thenReturn(java.util.Optional.empty());

        assertThrows(LinkNotFound.class, () -> linkService.redirectUrl(code));
    }

    @Test
    void seeLinks_returnListFromRepository() {
        String originalUrl1 = "https://meusite.com/c/67fa8b5b-3434-8006-922a" +
                "-d0385d0c1815";
        String originalUrl2 = "https://meusite.com/c/d0385d0c1815-3434-8006-922a" +
                "-67fa8b5b";
        String code1 = "qbPauCaPQfgoTwL61903";
        String shortUrl1 = "http://short.local/" + code1;
        String code2 = "goTwLqbPauCaPQf03619";
        String shortUrl2 = "http://short.local/" + code2;

        List<Link> links = List.of(new Link(shortUrl1, originalUrl1),
                new Link(shortUrl2, originalUrl2));

        when(linkRepository.findAll()).thenReturn(links);

        List<Link> result = linkService.seeLinks();

        assertEquals(2, result.size());

        assertEquals(shortUrl1, result.get(0).getShortCode());
        assertEquals(originalUrl1, result.get(0).getOriginalUrl());

        assertEquals(shortUrl2, result.get(1).getShortCode());
        assertEquals(originalUrl2, result.get(1).getOriginalUrl());

    }
}
