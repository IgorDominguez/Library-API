package com.github.igordominguez.libraryapi.service;

import com.github.igordominguez.libraryapi.dto.request.AuthorRequestDTO;
import com.github.igordominguez.libraryapi.dto.response.AuthorResponseDTO;
import com.github.igordominguez.libraryapi.exception.AuthorNotFoundException;
import com.github.igordominguez.libraryapi.model.AuthorModel;
import com.github.igordominguez.libraryapi.repositories.AuthorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    public AuthorResponseDTO add(AuthorRequestDTO authorRequestDTO) {

        AuthorModel authorModel = new AuthorModel();

        authorModel.setName(authorRequestDTO.name());
        authorModel.setDateBirth(authorRequestDTO.dateBirth());
        authorModel.setNationality(authorRequestDTO.nationality());

        try {

            authorRepository.save(authorModel);
            return toAuthorResponseDTO(authorModel);
        } catch (RuntimeException e) {

            throw new RuntimeException("Erro: " + e);
        }
    }

    public AuthorResponseDTO findAuthorById(Long id) {

        return authorRepository.findById(id)
                .map(a -> toAuthorResponseDTO(a))
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public List<AuthorResponseDTO> getAllAuthors() {

        try {

            return authorRepository.findAll().stream()
                    .map(a -> toAuthorResponseDTO(a))
                    .toList();

        } catch (DataAccessException e) {

            throw new DataAccessException("Banco de dados não está criado/rodando") {};
        }
    }

    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {

        AuthorModel authorModel = authorRepository.findById(id)
                        .orElseThrow(() -> new AuthorNotFoundException(id));

        authorModel.setName(authorRequestDTO.name());
        authorModel.setNationality(authorRequestDTO.nationality());
        authorModel.setDateBirth(authorRequestDTO.dateBirth());

        authorRepository.save(authorModel);
        return toAuthorResponseDTO(authorModel);
    }

    public void deleteAuthor(Long id) {

        AuthorModel authorModel = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        authorRepository.delete(authorModel);
    }

    private AuthorResponseDTO toAuthorResponseDTO(AuthorModel authorModel) {

        return new AuthorResponseDTO(
                authorModel.getId(),
                authorModel.getName(),
                authorModel.getNationality(),
                authorModel.getDateBirth()
        );
    }
}
