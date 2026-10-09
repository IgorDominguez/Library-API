package com.github.igordominguez.libraryapi.dto.request;

import java.time.LocalDateTime;

public record BookRequestDTO(
        String title,
        Integer yearPublish,
        Long authorId
) {}
