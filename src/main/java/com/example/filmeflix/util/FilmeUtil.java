package com.example.filmeflix.util;

import com.example.filmeflix.dto.FilmeDTO;
import com.example.filmeflix.dto.FilmeResponse;
import com.example.filmeflix.model.Filme;
import com.example.filmeflix.model.Genero;
import com.example.filmeflix.repository.GeneroRepository;
import com.example.filmeflix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;

public class FilmeUtil {


	public static Filme parseFilmeDTOIntoFilme(FilmeDTO dto) {

		return Filme.builder()
				.nome(dto.getNome())
				.diretor(dto.getDiretor())
				.sipnose(dto.getSipnose())
				.genero(Genero.builder().id(dto.getGeneroId()).build())
				.build();
	}

	public static Filme parseFilmeDTOIntoFilme(FilmeDTO dto,Genero genero) {

		return Filme.builder()
				.nome(dto.getNome())
				.diretor(dto.getDiretor())
				.sipnose(dto.getSipnose())
				.genero(genero)
				.build();
	}

	public static FilmeResponse parseFilmeIntoFilmeResponse(Filme filme) {
		return FilmeResponse.builder()
				.id(filme.getId())
				.nome(filme.getNome())
				.diretor(filme.getDiretor())
				.sipnose(filme.getSipnose())
				.generoNome(filme.getGenero().getNome())
				.build();
	}

}
