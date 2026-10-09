package com.github.igordominguez.libraryapi.dto.response;

import com.github.igordominguez.libraryapi.enums.Nationality;

import java.time.LocalDate;

public record AuthorResponseDTO(
        Long id,
        String name,
        Nationality nationality,
        LocalDate dateBirth
) {}
