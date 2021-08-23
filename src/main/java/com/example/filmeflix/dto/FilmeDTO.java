package com.example.filmeflix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO {

    private String nome;
    private String diretor;
    private String generoId;
    private String sipnose;

}
