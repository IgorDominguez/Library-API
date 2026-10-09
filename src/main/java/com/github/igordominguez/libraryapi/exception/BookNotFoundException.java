package com.github.igordominguez.libraryapi.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Livro não encontrado de id: " + id);
    }
}
