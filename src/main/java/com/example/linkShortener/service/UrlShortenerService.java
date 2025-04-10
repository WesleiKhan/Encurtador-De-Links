package com.example.linkShortener.service;

import com.example.linkShortener.repositorie.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlShortenerService {

    private final LinkRepository linkRepository;

    private static final String ABC_LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final String NUMBERS = "0123456789";

    private static final Integer LETTERS_LENGTH = 15;

    private static final Integer NUMBERS_LENGTH = 5;

    private final Random random = new Random();

    public UrlShortenerService(LinkRepository linkRepository) {

        this.linkRepository = linkRepository;
    }

    public String generatedUniqueCode() {
        String code;
        do {

            code = generatedRandomCode();

        }while (linkRepository.existsByShortCode(code));

        return code;
    }

    private String generatedRandomCode() {
        StringBuilder code = new StringBuilder();

        for(int i = 0; i < LETTERS_LENGTH; i++) {
           code.append(ABC_LETTERS.charAt(random.nextInt(ABC_LETTERS.length())));
        }

        for(int i = 0; i < NUMBERS_LENGTH; i++) {
            code.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }

        return code.toString();
    }
}
