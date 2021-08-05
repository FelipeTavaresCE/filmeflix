package com.example.filmeflix.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.filmeflix.FilmeflixApplication;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(FilmeController.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringJUnitConfig(FilmeControllerTest.class)
@SpringBootTest
@ContextConfiguration(classes = FilmeflixApplication.class)
public class FilmeControllerTest {
	
}
