package com.example.filmeflix.service;

import com.example.filmeflix.dto.FilmeDTO;
import com.example.filmeflix.model.Filme;

import java.util.List;

public interface FilmeService {
    Filme save(FilmeDTO dto);

    Filme findById(Long id);

	void delete(long id);

    List<Filme> findLatestMovies();
}
