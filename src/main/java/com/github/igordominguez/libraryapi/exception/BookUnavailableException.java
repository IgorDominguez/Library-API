package com.github.igordominguez.libraryapi.exception;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(Long id) {
        super("Livro não disponível para emprestimo de id: " + id);
    }
}
