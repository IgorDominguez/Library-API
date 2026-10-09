package com.github.igordominguez.libraryapi.exception;

import com.github.igordominguez.libraryapi.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super("Autor não encontrado de id: " + id);
    }
}
