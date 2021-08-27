package com.example.filmeflix.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import com.example.filmeflix.model.Genero;
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

    @MockBean
    private FilmeService service;

    @MockBean
    private FilmeRepository repository;

    private static final String FILME_URI = "/v1/filmes";

    @SneakyThrows
    @Test
    void deveConsultarUmaListaFilmes() {
    	String filmeId = "1";
        List<Filme> filmes = new ArrayList<>();
        filmes.add(buildFilme(filmeId));

        BDDMockito.given(service.findLatestMovies()).willReturn(filmes);

        assertDoesNotThrow(() -> service.findLatestMovies());
        this.mockMvc
	        .perform(get(FILME_URI)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("[0].id").exists());        
    }

    @Test
    @SneakyThrows
    @DisplayName("Deve criar um filme com sucesso")
    void testCreateMovie() {
    	String filmeId = "1";
        FilmeDTO dto = getDto();
        Filme savedFilme = buildFilme(filmeId);

        BDDMockito.when(service.save(Mockito.any(FilmeDTO.class)))
                .thenReturn(savedFilme);

        this.mockMvc
                .perform(post(FILME_URI)
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }
    
    @Test
    @SneakyThrows
	@DisplayName("Deve excluir um filme que perdeu a relevância")
	public void deleteMovieTest() throws Exception {
		String filmeId = "1";
		Filme filmeEntity = buildFilme(filmeId);
		
		BDDMockito.when(repository.findById(filmeId)).thenReturn(Optional.of(filmeEntity));
		
		MockHttpServletRequestBuilder req = MockMvcRequestBuilders
				.delete(FILME_URI + "/" + filmeId)
				.header("Accept-Language", "pt-BR")
				.accept(MediaType.APPLICATION_JSON);

		assertDoesNotThrow(() -> service.delete(Mockito.anyString()));
		mockMvc.perform(req).andExpect(status().isOk());
	}
    
    private FilmeDTO getDto() {
        return FilmeDTO.builder().generoId("1").build();
    }
    
    private Filme buildFilme(String filmeId) {
		return Filme.builder()
				.id(filmeId)
				.genero(Genero.builder().id("1").nome("Comédia").build())
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
