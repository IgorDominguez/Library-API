package com.github.igordominguez.libraryapi.controller;

import com.github.igordominguez.libraryapi.dto.request.AuthorRequestDTO;
import com.github.igordominguez.libraryapi.dto.response.AuthorResponseDTO;
import com.github.igordominguez.libraryapi.exception.AuthorNotFoundException;
import com.github.igordominguez.libraryapi.model.AuthorModel;
import com.github.igordominguez.libraryapi.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/author")
@Tag(name = "Author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {

        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorResponseDTO> addAuthor(AuthorRequestDTO author) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorService.add(author));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAuthorById(Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorService.findAuthorById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAuthor(Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorService.updateAuthor(id, authorRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(Long id) {

        authorService.deleteAuthor(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
