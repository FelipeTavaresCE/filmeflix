package com.example.filmeflix.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FilmeResponse {

	private Long id;
    private String nome;
    private String diretor;
    private Integer generoId;
    private String sipnose;

}
