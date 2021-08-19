package com.example.filmeflix.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.filmeflix.FilmeflixApplication;
import com.example.filmeflix.dto.FilmeDTO;
import com.example.filmeflix.model.Filme;
import com.example.filmeflix.repository.FilmeRepository;
import com.example.filmeflix.service.FilmeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(FilmeController.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringJUnitConfig(FilmeControllerTest.class)
@SpringBootTest
@ContextConfiguration(classes = FilmeflixApplication.class)
public class FilmeControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FilmeService service;

    @Mock
    private FilmeRepository repository;

    private static final String FILME_URI = "/v1/filmes";


    @SneakyThrows
    @Test
    void deveConsultarUmaListaFilmes() {
        List<Filme> filmes = new ArrayList<>();
        filmes.add(Filme.builder()
                .id("1")
                .build());

        Mockito.when(service.findLatestMovies()).thenReturn(filmes);

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders
                .get(FILME_URI)
                .header("Accept-Language", "pt-BR")
                .accept(MediaType.APPLICATION_JSON);

        assertDoesNotThrow(() -> service.findLatestMovies());
        mockMvc.perform(req).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DisplayName("Deve criar um filme com sucesso")
    void testCreateMovie() {
        FilmeDTO dto = getDto();
        Filme savedFilme = service.save(dto);

        Mockito.when(service.findById("1")).thenReturn(null);
        Mockito.when(repository.save(Mockito.any(Filme.class)))
                .thenReturn(savedFilme);

        this.mockMvc
                .perform(post(FILME_URI)
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

    }
    
    @Test
    @SneakyThrows
	@DisplayName("Deve excluir um filme que perdeu a relevância")
	public void deleteMovieTest() throws Exception {
		String filmeId = "1";
		Filme filmeEntity = buildFilme(filmeId);
		
		BDDMockito.given(repository.findById(Mockito.anyString())).willReturn(Optional.of(filmeEntity));
		
		MockHttpServletRequestBuilder req = MockMvcRequestBuilders
				.delete(FILME_URI + "/" + filmeId)
				.header("Accept-Language", "pt-BR")
				.accept(MediaType.APPLICATION_JSON);
		
		assertDoesNotThrow(() -> service.delete(Mockito.anyString()));
		mockMvc.perform(req).andExpect(status().isOk());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyString());
	}
    
    @Test
    @SneakyThrows
	@DisplayName("Deve retornar erro ao tentar excluir um filme que não existe")
	public void returnErrorOnDeleteNotExistentMovieTest() throws Exception {
		long filmeId = 1l;

		BDDMockito.given(repository.findById(Mockito.anyString())).willReturn(Optional.empty());
		
		MockHttpServletRequestBuilder req = MockMvcRequestBuilders
				.delete(FILME_URI + "/" + filmeId)
				.header("Accept-Language", "pt-BR")
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(req).andExpect(status().isNotFound());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyString());
	}

    private FilmeDTO getDto() {
        return FilmeDTO.builder().build();
    }
    
    private Filme buildFilme(String filmeId) {
		return Filme.builder()
				.id(filmeId)
				.build();
	}

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
