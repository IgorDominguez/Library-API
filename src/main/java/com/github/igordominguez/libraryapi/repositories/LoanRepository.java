package com.github.igordominguez.libraryapi.repositories;

import com.github.igordominguez.libraryapi.model.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {

    @Query(value = "SELECT * FROM loan WHERE reader_id_id = :readerId", nativeQuery = true)
    List<LoanModel> findLoansOfReaderById(Long readerId);
}
