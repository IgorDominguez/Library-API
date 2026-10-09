package com.github.igordominguez.libraryapi.dto.response;

import com.github.igordominguez.libraryapi.dto.request.AuthorRequestDTO;

import java.time.LocalDateTime;

public record BookResponseDTO(
        Long id,
        String title,
        Integer yearPublish,
        boolean available,
        AuthorRequestDTO author
) {}
