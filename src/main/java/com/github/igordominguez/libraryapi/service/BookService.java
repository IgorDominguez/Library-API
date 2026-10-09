package com.github.igordominguez.libraryapi.service;

import com.github.igordominguez.libraryapi.exception.AuthorNotFoundException;
import com.github.igordominguez.libraryapi.exception.BookNotFoundException;
import com.github.igordominguez.libraryapi.model.AuthorModel;
import com.github.igordominguez.libraryapi.model.BookModel;
import com.github.igordominguez.libraryapi.repositories.AuthorRepository;
import com.github.igordominguez.libraryapi.repositories.BookRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.github.igordominguez.libraryapi.dto.response.BookResponseDTO;
import com.github.igordominguez.libraryapi.dto.request.BookRequestDTO;
import com.github.igordominguez.libraryapi.dto.request.AuthorRequestDTO;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookResponseDTO addBook(BookRequestDTO book) {

        AuthorModel author = authorRepository.findById(book.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(book.authorId()));

        BookModel bookModel = new BookModel();

        bookModel.setAuthor(author);
        bookModel.setYearPublish(book.yearPublish());
        bookModel.setTitle(book.title());
        bookModel.setAvailable(true);

        bookRepository.save(bookModel);

        return toBookResponseDTO(bookModel);
    }

    public BookResponseDTO findBookById(Long id) {

        return bookRepository.findById(id)
                .map(b -> toBookResponseDTO(b))
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<BookResponseDTO> getAllBooks() {
        try {
            return bookRepository.findAll().stream()
                    .map(b -> toBookResponseDTO(b))
                    .toList();
        } catch (DataAccessException e) {

            throw new DataAccessException("Banco de dados não está criado/rodando") {};
        }
    }

    public List<BookResponseDTO> getAvailableBooks() {
        return bookRepository.findBooksAvailable().stream()
                .map(b -> toBookResponseDTO(b))
                .toList();
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {

        BookModel bookModel = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookModel.setTitle(bookRequestDTO.title());
        bookModel.setYearPublish(bookRequestDTO.yearPublish());

        bookRepository.save(bookModel);

        return toBookResponseDTO(bookModel);
    }

    public void deleteBook(Long id) {

        BookModel bookModel = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(bookModel);
    }

    private BookResponseDTO toBookResponseDTO(BookModel bookModel) {

        return new BookResponseDTO(
                bookModel.getId(),
                bookModel.getTitle(),
                bookModel.getYearPublish(),
                bookModel.isAvailable(),
                new AuthorRequestDTO(
                        bookModel.getAuthor().getName(),
                        bookModel.getAuthor().getNationality(),
                        bookModel.getAuthor().getDateBirth()
                )
        );
    }
}
