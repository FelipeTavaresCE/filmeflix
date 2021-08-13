package com.example.filmeflix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "filme")
public class Filme {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="diretor")
    private String diretor;

	@Column(name="sipnose")
    private String sipnose;

	@ManyToOne
	@JoinColumn(name="genero_id")
    private Genero genero;
}
