package com.github.igordominguez.libraryapi.exception;

public class LoanNotFound extends RuntimeException {
    public LoanNotFound(Long id) {
        super("Emprestimo não encontrado de id: " + id);
    }
}
