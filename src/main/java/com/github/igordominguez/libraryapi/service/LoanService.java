package com.github.igordominguez.libraryapi.service;

import com.github.igordominguez.libraryapi.dto.request.AuthorRequestDTO;
import com.github.igordominguez.libraryapi.dto.request.LoanRequestDTO;
import com.github.igordominguez.libraryapi.dto.request.ReaderRequestDTO;
import com.github.igordominguez.libraryapi.dto.response.AuthorResponseDTO;
import com.github.igordominguez.libraryapi.dto.response.BookResponseDTO;
import com.github.igordominguez.libraryapi.dto.response.LoanResponseDTO;
import com.github.igordominguez.libraryapi.dto.response.ReaderResponseDTO;
import com.github.igordominguez.libraryapi.enums.LoanStatus;
import com.github.igordominguez.libraryapi.exception.BookNotFoundException;
import com.github.igordominguez.libraryapi.exception.BookUnavailableException;
import com.github.igordominguez.libraryapi.exception.LoanNotFound;
import com.github.igordominguez.libraryapi.exception.ReaderNotFoundException;
import com.github.igordominguez.libraryapi.model.BookModel;
import com.github.igordominguez.libraryapi.model.LoanModel;
import com.github.igordominguez.libraryapi.model.ReaderModel;
import com.github.igordominguez.libraryapi.repositories.BookRepository;
import com.github.igordominguez.libraryapi.repositories.LoanRepository;
import com.github.igordominguez.libraryapi.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, ReaderRepository readerRepository) {

        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public List<LoanResponseDTO> getAllLoans() {

        return loanRepository.findAll().stream()
                .map(l -> toLoanResponseDTO(l))
                .toList();
    }

    public LoanResponseDTO addLoan(LoanRequestDTO request) {

        BookModel book = bookRepository.findById(request.livroId())
                .orElseThrow(() -> new BookNotFoundException(request.livroId()));

        ReaderModel reader = readerRepository.findById(request.leitorId())
                .orElseThrow(() -> new ReaderNotFoundException(request.leitorId()));

        if (!book.isAvailable()) {

            throw new BookUnavailableException(request.livroId());
        }

        book.setAvailable(false);

        LoanModel loanModel = new LoanModel();

        loanModel.setBookId(book);
        loanModel.setReaderId(reader);
        loanModel.setLoanDateTime(LocalDateTime.now());
        loanModel.setLoanStatus(LoanStatus.LOANED);

        loanRepository.save(loanModel);

        return toLoanResponseDTO(loanModel);
    }

    public void returnBook(Long loanId) {

        LoanModel loanModel = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFound(loanId));

        loanModel.setLoanStatus(LoanStatus.AVAILABLE);
        loanModel.setReturnDateTime(LocalDateTime.now());

        BookModel bookModel = bookRepository.findById(loanModel.getBook_id().getId())
                .orElseThrow(() -> new BookNotFoundException(loanModel.getBook_id().getId()));

        bookModel.setAvailable(true);

        loanRepository.save(loanModel);
        bookRepository.save(bookModel);
    }

    public List<LoanResponseDTO> getLoansOfReaderById(Long readerId) {

        ReaderModel reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new ReaderNotFoundException(readerId));

        return loanRepository.findLoansOfReaderById(readerId).stream()
                .map(l -> toLoanResponseDTO(l))
                .toList();
    }

    public LoanResponseDTO findLoanById(Long loanId) {

        LoanModel loanModel = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFound(loanId));

        return toLoanResponseDTO(loanModel);
    }

    private LoanResponseDTO toLoanResponseDTO(LoanModel loanModel) {
        return new LoanResponseDTO(
                loanModel.getId(),
                new BookResponseDTO(
                        loanModel.getBook_id().getId(),
                        loanModel.getBook_id().getTitle(),
                        loanModel.getBook_id().getYearPublish(),
                        loanModel.getBook_id().isAvailable(),
                        new AuthorRequestDTO(
                                loanModel.getBook_id().getAuthor().getName(),
                                loanModel.getBook_id().getAuthor().getNationality(),
                                loanModel.getBook_id().getAuthor().getDateBirth()
                        )
                ),
                new ReaderResponseDTO(
                        loanModel.getReader_id().getId(),
                        loanModel.getReader_id().getName(),
                        loanModel.getReader_id().getEmail()
                ),
                loanModel.getLoanDateTime(),
                loanModel.getReturnDateTime(),
                loanModel.getLoanStatus()
        );
    }
}
