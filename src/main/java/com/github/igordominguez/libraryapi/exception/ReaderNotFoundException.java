package com.github.igordominguez.libraryapi.exception;

public class ReaderNotFoundException extends RuntimeException {
    public ReaderNotFoundException(Long id) {
        super("Leitor não encontrado de id: " + id);
    }
}
