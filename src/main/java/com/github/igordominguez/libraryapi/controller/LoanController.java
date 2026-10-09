package com.github.igordominguez.libraryapi.controller;

import com.github.igordominguez.libraryapi.dto.request.BookRequestDTO;
import com.github.igordominguez.libraryapi.dto.request.LoanRequestDTO;
import com.github.igordominguez.libraryapi.dto.response.BookResponseDTO;
import com.github.igordominguez.libraryapi.dto.response.LoanResponseDTO;
import com.github.igordominguez.libraryapi.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {

        this.loanService = loanService;
    }

    @PostMapping("/add")
    public ResponseEntity<LoanResponseDTO> addLoan(LoanRequestDTO loan) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loanService.addLoan(loan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getLoanById(Long loanId) {

        loanService.returnBook(loanId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanService.getAllLoans());
    }

    @GetMapping("/{readerId}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansOfReaderById(Long readerId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanService.getLoansOfReaderById(readerId));
    }

    @PutMapping("/return/book/{loanId}")
    public ResponseEntity<Void> returnBook(Long loanId) {

        loanService.returnBook(loanId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
