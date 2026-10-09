package com.github.igordominguez.libraryapi.repositories;

import com.github.igordominguez.libraryapi.model.ReaderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderModel, Long> {
}
