package com.example.filmeflix.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FilmeResponse {

	private String id;
    private String nome;
    private String diretor;
    private String generoNome;
    private String sipnose;

}
