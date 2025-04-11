package com.example.linkShortener.exceptions;

public class InvalidEntryException extends RuntimeException{

    public InvalidEntryException() {super("Url vazia Por favor tentar " +
            "novamente com valor valido.");}

    public InvalidEntryException(String message) {super(message);}
}
