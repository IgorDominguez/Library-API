package com.github.igordominguez.libraryapi.service;

import com.github.igordominguez.libraryapi.dto.request.ReaderRequestDTO;
import com.github.igordominguez.libraryapi.dto.response.ReaderResponseDTO;
import com.github.igordominguez.libraryapi.exception.ReaderNotFoundException;
import com.github.igordominguez.libraryapi.model.ReaderModel;
import com.github.igordominguez.libraryapi.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public ReaderResponseDTO addReader(ReaderRequestDTO readerDTO) {

        ReaderModel readerModel = new ReaderModel();

        readerModel.setName(readerDTO.nome());
        readerModel.setEmail(readerDTO.email());

        readerRepository.save(readerModel);

        return toReaderResponseDTO(readerModel);
    }

    public ReaderResponseDTO findReaderById(Long id) {

        return readerRepository.findById(id)
                .map(r -> toReaderResponseDTO(r))
                .orElseThrow(() -> new ReaderNotFoundException(id));
    }

    public List<ReaderResponseDTO> getAllReaders() {

        return readerRepository.findAll().stream()
                .map(r -> toReaderResponseDTO(r))
                .toList();
    }

    public ReaderResponseDTO updateReader(Long id, ReaderRequestDTO readerDTO) {

        ReaderModel readerModel = readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));

        readerModel.setName(readerDTO.nome());
        readerModel.setEmail(readerDTO.email());

        readerRepository.save(readerModel);

        return toReaderResponseDTO(readerModel);
    }

    public void deleteReader(Long id) {

        ReaderModel readerModel = readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));

        readerRepository.delete(readerModel);
    }

    private ReaderResponseDTO toReaderResponseDTO(ReaderModel readerModel) {

        return new ReaderResponseDTO(
                readerModel.getId(),
                readerModel.getName(),
                readerModel.getEmail()
        );
    }
}
