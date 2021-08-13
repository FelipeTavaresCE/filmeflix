package com.example.filmeflix.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "genero")
public class Genero {

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nome")
	private String nome;

	@OneToMany(mappedBy = "genero")
	private List<Filme> filmes;
}
