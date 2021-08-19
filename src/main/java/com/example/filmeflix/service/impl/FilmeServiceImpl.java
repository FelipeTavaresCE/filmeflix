package com.example.filmeflix.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.filmeflix.dto.FilmeDTO;
import com.example.filmeflix.model.Filme;
import com.example.filmeflix.repository.FilmeRepository;
import com.example.filmeflix.service.FilmeService;
import com.example.filmeflix.util.FilmeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmeServiceImpl implements FilmeService {
	private final FilmeRepository repository;
	
	@Override
    public Filme save(FilmeDTO dto) {
    	return repository.save(FilmeUtil.parseFilmeDTOIntoFilme(dto));
    }

	@Override
	public Filme findById(String id) {
    	return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found"));
    }

	@Override
	public void delete(String id) {
		repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found"));
		repository.deleteById(id);
	}

	@Override
	public List<Filme> findLatestMovies() {
		return repository.findAll();
	}
}
