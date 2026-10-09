package com.github.igordominguez.libraryapi.dto.request;

import com.github.igordominguez.libraryapi.enums.Nationality;

import java.time.LocalDate;

public record AuthorRequestDTO(
        String name,
        Nationality nationality,
        LocalDate dateBirth
) {}
