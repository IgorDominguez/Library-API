package com.github.igordominguez.libraryapi.dto.response;

import com.github.igordominguez.libraryapi.enums.LoanStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanResponseDTO(
        Long id,
        BookResponseDTO book,
        ReaderResponseDTO reader,
        LocalDateTime loanDateTime,
        LoanStatus loanStatus
) {}
