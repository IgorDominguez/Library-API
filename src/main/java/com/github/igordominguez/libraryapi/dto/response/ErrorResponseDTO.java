package com.github.igordominguez.libraryapi.dto.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        LocalDateTime timestamp,
        HttpStatus status,
        String erro
) {}
