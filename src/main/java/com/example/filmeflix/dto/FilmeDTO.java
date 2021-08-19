package com.example.filmeflix.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FilmeDTO {

    private String nome;
    private String diretor;
    private String generoId;
    private String sipnose;

}
