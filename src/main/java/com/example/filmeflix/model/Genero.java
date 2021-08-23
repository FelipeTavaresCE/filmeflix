package com.example.filmeflix.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Builder
@Entity
@Document
public class Genero implements Serializable {

    @Id
	private String id;
	private String nome;

}
