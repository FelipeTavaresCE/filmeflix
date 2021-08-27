package com.example.filmeflix.service;

import com.example.filmeflix.dto.FilmeDTO;
import com.example.filmeflix.model.Filme;
import com.example.filmeflix.model.Genero;
import com.example.filmeflix.repository.FilmeRepository;
import com.example.filmeflix.repository.GeneroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest()
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class FilmeServiceTest {

	@MockBean
	private FilmeService service;
	
	@MockBean
	private FilmeRepository repository;

	@MockBean
	private GeneroRepository generoRepository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	public void deveConsultarFilmes() {

		List<Filme> filmes = new ArrayList<>();
		filmes.add(Filme.builder()
				.id("1")
				.build());

		when(repository.findAll()).thenReturn(filmes);

		List<Filme> list = service.findLatestMovies();

		assertThat(list, not(equalTo(null)));
	}

	@Test
	public void deveCriarFilme() {

		Filme filme = Filme.builder()
				.id("1")
				.build();
		FilmeDTO filmeDTO = FilmeDTO.builder().
				nome("teste")
				.generoId("1")
				.build();

		Genero genero = Genero.builder().id("1").build();

		when(generoRepository.findById(filmeDTO.getGeneroId())).thenReturn(Optional.of(genero));
		when(service.save(filmeDTO)).thenReturn(filme);

		Filme filmeSaved = service.save(filmeDTO);

		assertThat(filmeSaved, not(equalTo(null)));
	}


}
