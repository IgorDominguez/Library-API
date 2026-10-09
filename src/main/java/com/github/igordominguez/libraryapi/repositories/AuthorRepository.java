package com.github.igordominguez.libraryapi.repositories;

import com.github.igordominguez.libraryapi.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
}
