package com.example.filmeflix.repository;

import com.example.filmeflix.model.Filme;
import com.example.filmeflix.model.Genero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends MongoRepository<Genero, String>{
	
}
