package com.github.igordominguez.libraryapi.model;

import com.github.igordominguez.libraryapi.enums.LoanStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
public class LoanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private BookModel book_id;
    @ManyToOne
    private ReaderModel reader_id;
    private LocalDateTime loanDateTime;
    private LocalDateTime returnDateTime;
    private LoanStatus loanStatus;


    public Long getId() {
        return id;
    }

    public BookModel getBook_id() {
        return book_id;
    }

    public void setBookId(BookModel book_id) {
        this.book_id = book_id;
    }

    public ReaderModel getReader_id() {
        return reader_id;
    }

    public void setReaderId(ReaderModel reader_id) {
        this.reader_id = reader_id;
    }

    public LocalDateTime getLoanDateTime() {
        return loanDateTime;
    }

    public void setLoanDateTime(LocalDateTime loanDateTime) {
        this.loanDateTime = loanDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }
}
