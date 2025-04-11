package com.example.linkShortener.exceptions;


public class LinkNotFound extends RuntimeException{

    public LinkNotFound() {super("Link não foi encontrado.");};

    public LinkNotFound(String message) {super(message);}
}
