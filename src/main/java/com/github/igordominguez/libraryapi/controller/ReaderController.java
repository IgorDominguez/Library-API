package com.github.igordominguez.libraryapi.controller;

import com.github.igordominguez.libraryapi.dto.request.ReaderRequestDTO;
import com.github.igordominguez.libraryapi.dto.response.ReaderResponseDTO;
import com.github.igordominguez.libraryapi.service.ReaderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reader")
@Tag(name = "Reader")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {

        this.readerService = readerService;
    }

    @PostMapping("/add")
    public ResponseEntity<ReaderResponseDTO> addReader(ReaderRequestDTO reader) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(readerService.addReader(reader));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderResponseDTO> getReaderById(Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(readerService.findReaderById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReaderResponseDTO>> getAllReaders() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(readerService.getAllReaders());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReaderResponseDTO> updateReader(Long id, ReaderRequestDTO reader) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(readerService.updateReader(id, reader));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReader(Long id) {

        readerService.deleteReader(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
