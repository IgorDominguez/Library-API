package com.github.igordominguez.libraryapi.repositories;

import com.github.igordominguez.libraryapi.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    @Query(value = "SELECT * FROM book WHERE available = true", nativeQuery = true)
    List<BookModel> findBooksAvailable();

    @Query(value = "SELECT * FROM book WHERE title = :title", nativeQuery = true)
    List<BookModel> findBooksByTitle(String title);
}
