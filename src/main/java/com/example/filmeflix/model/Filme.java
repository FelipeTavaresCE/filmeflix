package com.example.filmeflix.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Data
@Builder
@Entity
@Document
public class Filme {

	private Long id;
	private String nome;
    private String diretor;
    private String sipnose;
    private Genero genero;
}
