package com.example.linkShortener.service;

import com.example.linkShortener.repositorie.LinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlShortenerServiceTest {

    @Mock
    LinkRepository linkRepository;

    @InjectMocks
    UrlShortenerService urlShortenerService;

    @Test
    void generatedUniqueCode_whenCodeExistItGeneratesAnother() {

        when(linkRepository.existsByShortCode(anyString()))
                .thenReturn(true)
                .thenReturn(false);

        String code = urlShortenerService.generatedUniqueCode();

        assertNotNull(code);
        verify(linkRepository, times(2))
                .existsByShortCode(anyString());

    }
}
