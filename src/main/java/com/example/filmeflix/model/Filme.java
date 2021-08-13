package com.example.filmeflix.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Filme {

    private Long id;
    private String nome;
    private String Diretor;
    private List<String> Atores;
    private String auditoria;
}
