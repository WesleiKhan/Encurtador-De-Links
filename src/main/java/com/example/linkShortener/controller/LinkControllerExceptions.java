package com.example.linkShortener.controller;

import com.example.linkShortener.exceptions.InvalidEntryException;
import com.example.linkShortener.exceptions.LinkNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LinkControllerExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LinkNotFound.class)
    private ResponseEntity<String> linkNotFound(LinkNotFound message) {

        Map<String, String> erro = new HashMap<>();
        erro.put("Erro", message.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro.get(
                "Erro"));
    }

    @ExceptionHandler(IOException.class)
    private ResponseEntity<String> internalErro(IOException message) {

        Map<String, String> erro = new HashMap<>();
        erro.put("Erro", "Erro Interno Estamos trabalhando para consertar.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro.get("Erro"));
    }

    @ExceptionHandler(InvalidEntryException.class)
    private ResponseEntity<String> invalidEntryException(InvalidEntryException e) {

        Map<String, String> erro = new HashMap<>();
        erro.put("Erro", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(erro.get("Erro"));
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> rutimeException(RuntimeException message) {

        Map<String, String> erro = new HashMap<>();
        erro.put("Erro", "Erro ao realizar solocitação Por favor tentar " +
                "novamnete.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro.get(
                "Erro"));
    }
}
