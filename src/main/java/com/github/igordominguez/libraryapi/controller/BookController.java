package com.github.igordominguez.libraryapi.controller;

import com.github.igordominguez.libraryapi.dto.request.BookRequestDTO;
import com.github.igordominguez.libraryapi.dto.response.BookResponseDTO;
import com.github.igordominguez.libraryapi.repositories.BookRepository;
import com.github.igordominguez.libraryapi.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/book")
@Tag(name = "Book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponseDTO> addBook(BookRequestDTO book) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.addBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.findBookById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getAllBooks());
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookResponseDTO>> getAvailableBooks() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getAvailableBooks());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(Long id, BookRequestDTO book) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.updateBook(id, book));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(Long id) {

        bookService.deleteBook(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
