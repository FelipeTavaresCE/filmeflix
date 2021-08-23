package com.example.filmeflix.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.filmeflix.repository.FilmeRepository;
import com.example.filmeflix.service.impl.FilmeServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class FilmeServiceTest {
	
	private FilmeService service;
	
	@MockBean
	private FilmeRepository repository;
	
	@BeforeEach
	public void setUp() {
//		this.service = new FilmeServiceImpl(repository);
	}
	
	
	
	
	
}
