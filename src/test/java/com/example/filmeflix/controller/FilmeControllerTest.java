package com.example.filmeflix.controller;

import com.example.filmeflix.FilmeflixApplication;
import com.example.filmeflix.dto.FilmeDTO;
import com.example.filmeflix.model.Filme;
import com.example.filmeflix.repository.FilmeRepository;
import com.example.filmeflix.service.FilmeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    @DisplayName("Deve criar um filme com sucesso")
    void testCreateMovie() throws Exception {


        FilmeDTO dto = getDto();
        Filme savedFilme = service.save(dto);

        Mockito.when(service.findById(1L)).thenReturn(null);
        Mockito.when(repository.save(Mockito.any(Filme.class)))
                .thenReturn(savedFilme);

        this.mockMvc
                .perform(post(FILME_URI)
                        .content(asJsonString(dto))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        }

    private FilmeDTO getDto() {
        return new FilmeDTO();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
