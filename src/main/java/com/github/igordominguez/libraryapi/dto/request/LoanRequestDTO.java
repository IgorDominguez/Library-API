package com.github.igordominguez.libraryapi.dto.request;

public record LoanRequestDTO(
        Long livroId,
        Long leitorId
) {}
